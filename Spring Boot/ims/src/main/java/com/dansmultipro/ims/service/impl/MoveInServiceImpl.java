package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.constant.HistoryTypeCode;
import com.dansmultipro.ims.constant.ResponseMessage;
import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;
import com.dansmultipro.ims.dto.moveindetail.CreateMoveInDetailRequestDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;
import com.dansmultipro.ims.exception.AlreadyExistsException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.*;
import com.dansmultipro.ims.repo.*;
import com.dansmultipro.ims.service.MoveInService;
import com.dansmultipro.ims.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MoveInServiceImpl extends BaseService implements MoveInService {

    private final MoveInRepo moveInRepo;
    private final MoveInDetailRepo moveInDetailRepo;
    private final SupplierRepo supplierRepo;
    private final ProductRepo productRepo;
    private final HistoryTypeRepo historyTypeRepo;
    private final MoveHistoryRepo moveHistoryRepo;

    public MoveInServiceImpl(MoveInRepo moveInRepo, MoveInDetailRepo moveInDetailRepo, SupplierRepo supplierRepo, ProductRepo productRepo, HistoryTypeRepo historyTypeRepo, MoveHistoryRepo moveHistoryRepo) {
        this.moveInRepo = moveInRepo;
        this.moveInDetailRepo = moveInDetailRepo;
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
        this.historyTypeRepo = historyTypeRepo;
        this.moveHistoryRepo = moveHistoryRepo;
    }

    @Override
    public PaginatedResponseDto<MoveInResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MoveIn> moveInPages = moveInRepo.findAll(pageable);

        List<MoveIn> moveInList = moveInPages.getContent();
        List<MoveInResponseDto> responseDtoList = moveInList.stream()
                .map(v -> new MoveInResponseDto(v.getId(), v.getCode(), v.getDate().toString(), v.getSupplier().getName()))
                .toList();

        PaginatedResponseDto<MoveInResponseDto> paginatedMoveInResponse = new PaginatedResponseDto<>(
                responseDtoList,
                moveInPages.getTotalElements()
        );
        return paginatedMoveInResponse;
    }

    @Override
    public List<MoveInDetailResponseDto> getById(String id) {
        UUID validId = validateUUID(id);
        moveInRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Move in supply not found")
        );
        List<MoveInDetailResponseDto> result = moveInDetailRepo.findByMoveInId(validId).stream()
                .map(v -> new MoveInDetailResponseDto(
                        v.getId(), v.getQuantity().toString(), v.getProduct().getName(),
                        v.getMoveIn().getCode()))
                .toList();
        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateMoveInRequestDto requestDto) {
        UUID validSupplierId = validateUUID(requestDto.getSupplierId());
        Supplier supplier = supplierRepo.findById(validSupplierId).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        HistoryType type = historyTypeRepo.findByCode(HistoryTypeCode.IN.name()).orElseThrow(
                () -> new NotFoundException("History type not found")
        );
        String code = RandomGenerator.randomizeCode(20);
        if (moveInRepo.findByCodeIgnoreCase(code).isPresent()) {
            throw new AlreadyExistsException("Move in with corresponding code already exist");
        }
        MoveIn moveInNew = new MoveIn();
        MoveIn moveInInsert = prepareForInsert(moveInNew);
        moveInInsert.setCode(code);
        moveInInsert.setDate(LocalDate.now());
        moveInInsert.setSupplier(supplier);
        MoveIn createdMoveIn = moveInRepo.save(moveInInsert);

        List<CreateMoveInDetailRequestDto> detailDtoList = requestDto.getMoveInDetailList();
        List<Product> addedProducts = new ArrayList<>();
        for (CreateMoveInDetailRequestDto detailDto : detailDtoList) {
            UUID validProductId = validateUUID(detailDto.getProductId());
            Product product = productRepo.findById(validProductId).orElseThrow(
                    () -> new NotFoundException("Product not found")
            );
            if (addedProducts.contains(product)) {
                throw new AlreadyExistsException("Found duplicate product");
            }
            addedProducts.add(product);
            Integer oldQty = product.getQuantity();
            Integer diffQty = detailDto.getQuantity();
            Integer newQty = oldQty + diffQty;

            MoveInDetail newMoveInDetail = prepareForInsert(new MoveInDetail());
            newMoveInDetail.setMoveIn(createdMoveIn);
            newMoveInDetail.setProduct(product);
            newMoveInDetail.setQuantity(diffQty);
            moveInDetailRepo.save(newMoveInDetail);

            prepareForUpdate(product);
            product.setQuantity(newQty);
            productRepo.save(product);

            MoveHistory newHistory = prepareForInsert(new MoveHistory());
            newHistory.setProduct(product);
            newHistory.setHistoryType(type);
            newHistory.setDate(LocalDate.now());
            newHistory.setQuantity(diffQty);
            newHistory.setNewQuantity(newQty);
            moveHistoryRepo.save(newHistory);
        }

        return new CreateResponseDto(createdMoveIn.getId(), ResponseMessage.CREATED.getMessage());
    }
}
