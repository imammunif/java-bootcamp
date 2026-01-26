package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;
import com.dansmultipro.ims.dto.moveindetail.CreateMoveInDetailRequestDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;
import com.dansmultipro.ims.exception.InvalidQuantityException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.MoveIn;
import com.dansmultipro.ims.model.MoveInDetail;
import com.dansmultipro.ims.model.Product;
import com.dansmultipro.ims.model.Supplier;
import com.dansmultipro.ims.repo.MoveInDetailRepo;
import com.dansmultipro.ims.repo.MoveInRepo;
import com.dansmultipro.ims.repo.ProductRepo;
import com.dansmultipro.ims.repo.SupplierRepo;
import com.dansmultipro.ims.service.MoveInService;
import com.dansmultipro.ims.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MoveInServiceImpl extends BaseService implements MoveInService {

    private final MoveInRepo moveInRepo;
    private final MoveInDetailRepo moveInDetailRepo;
    private final SupplierRepo supplierRepo;
    private final ProductRepo productRepo;

    public MoveInServiceImpl(MoveInRepo moveInRepo, MoveInDetailRepo moveInDetailRepo, SupplierRepo supplierRepo, ProductRepo productRepo) {
        this.moveInRepo = moveInRepo;
        this.moveInDetailRepo = moveInDetailRepo;
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<MoveInResponseDto> getAll() {
        List<MoveInResponseDto> result = moveInRepo.findAll().stream()
                .map(v -> new MoveInResponseDto(v.getId(), v.getCode(), v.getDate().toString(), v.getSupplier().getName()))
                .toList();
        return result;
    }

    @Override
    public List<MoveInDetailResponseDto> getById(String id) {
        MoveIn moveIn = moveInRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Move in supply not found")
        );
        List<MoveInDetailResponseDto> result = moveInDetailRepo.findByMoveInId(UUID.fromString(id)).stream()
                .map(v -> new MoveInDetailResponseDto(
                        v.getId(), v.getQuantity().toString(), v.getProduct().getName(),
                        v.getMoveIn().getCode()))
                .toList();
        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateMoveInRequestDto requestDto) {
        Supplier supplier = supplierRepo.findById(UUID.fromString(requestDto.getSupplierId())).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        List<CreateMoveInDetailRequestDto> detailDtoList = requestDto.getMoveInDetailList();
        MoveIn moveInNew = new MoveIn();
        MoveIn moveInInsert = prepareForInsert(moveInNew);
        moveInInsert.setCode(RandomGenerator.randomizeCode(20));
        moveInInsert.setDate(LocalDate.now());
        moveInInsert.setSupplier(supplier);
        MoveIn createdMoveIn = moveInRepo.save(moveInInsert);

        for (CreateMoveInDetailRequestDto detailDto : detailDtoList) {
            Product product = productRepo.findById(UUID.fromString(detailDto.getProductId())).orElseThrow(
                    () -> new NotFoundException("Product not found")
            );
            if (detailDto.getQuantity() <= 0) {
                throw new InvalidQuantityException("Quantity is not valid");
            }
            MoveInDetail newMoveInDetail = prepareForInsert(new MoveInDetail());
            newMoveInDetail.setMoveIn(createdMoveIn);
            newMoveInDetail.setProduct(product);
            newMoveInDetail.setQuantity(detailDto.getQuantity());
            moveInDetailRepo.save(newMoveInDetail);
        }

        return new CreateResponseDto(createdMoveIn.getId(), "Saved");
    }
}
