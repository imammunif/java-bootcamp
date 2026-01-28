package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.constant.ResponseMessage;
import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.supplier.CreateSupplierRequestDto;
import com.dansmultipro.ims.dto.supplier.SupplierResponseDto;
import com.dansmultipro.ims.dto.supplier.UpdateSupplierRequestDto;
import com.dansmultipro.ims.exception.AlreadyExistsException;
import com.dansmultipro.ims.exception.MissMatchException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.exception.ResourceInUseException;
import com.dansmultipro.ims.model.Supplier;
import com.dansmultipro.ims.repo.MoveInRepo;
import com.dansmultipro.ims.repo.SupplierRepo;
import com.dansmultipro.ims.service.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierServiceImpl extends BaseService implements SupplierService {

    private final SupplierRepo supplierRepo;
    private final MoveInRepo moveInRepo;

    public SupplierServiceImpl(SupplierRepo supplierRepo, MoveInRepo moveInRepo) {
        this.supplierRepo = supplierRepo;
        this.moveInRepo = moveInRepo;
    }

    @Override
    public PaginatedResponseDto<SupplierResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> supplierPages = supplierRepo.findAll(pageable);

        List<Supplier> supplierList = supplierPages.getContent();
        List<SupplierResponseDto> responseDtoList = supplierList.stream()
                .map(v -> new SupplierResponseDto(v.getId(), v.getCode(), v.getName(), v.getAddress(), v.getPhone(), v.getVersion().toString()))
                .toList();

        PaginatedResponseDto<SupplierResponseDto> paginatedSupplierResponse = new PaginatedResponseDto<>(
                responseDtoList,
                supplierPages.getTotalElements()
        );
        return paginatedSupplierResponse;
    }

    @Override
    public SupplierResponseDto getById(String id) {
        UUID validId = validateUUID(id);
        Supplier supplier = supplierRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        return new SupplierResponseDto(supplier.getId(), supplier.getCode(), supplier.getName(), supplier.getAddress(), supplier.getPhone(), supplier.getVersion().toString());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateSupplierRequestDto requestDto) {
        String requestCode = requestDto.getCode();
        if (supplierRepo.findByCodeIgnoreCase(requestCode).isPresent()) {
            throw new AlreadyExistsException("Supplier with corresponding code already exist");
        }
        String requestPhone = requestDto.getPhone();
        if (supplierRepo.findByPhone(requestPhone).isPresent()) {
            throw new AlreadyExistsException("Phone already exist");
        }
        Supplier supplierNew = new Supplier();
        Supplier supplierInsert = prepareForInsert(supplierNew);
        supplierInsert.setCode(requestCode);
        supplierInsert.setName(requestDto.getName());
        supplierInsert.setAddress(requestDto.getAddress());
        supplierInsert.setPhone(requestPhone);

        Supplier createdSupplier = supplierRepo.save(supplierInsert);
        return new CreateResponseDto(createdSupplier.getId(), ResponseMessage.CREATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateSupplierRequestDto requestDto) {
        UUID validId = validateUUID(id);
        Supplier supplier = supplierRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        if (!supplier.getVersion().equals(requestDto.getVersion())) {
            throw new MissMatchException("Version not match");
        }
        String requestCode = requestDto.getCode();
        if (!supplier.getCode().equals(requestCode)) {
            if (supplierRepo.findByCodeIgnoreCase(requestCode).isPresent()) {
                throw new AlreadyExistsException("Code already exist");
            }
        }
        String requestPhone = requestDto.getPhone();
        if (!supplier.getPhone().equals(requestPhone)) {
            if (supplierRepo.findByPhone(requestPhone).isPresent()) {
                throw new AlreadyExistsException("Phone already exist");
            }
        }
        Supplier supplierUpdate = prepareForUpdate(supplier);
        supplierUpdate.setCode(requestCode);
        supplierUpdate.setName(requestDto.getName());
        supplierUpdate.setAddress(requestDto.getAddress());
        supplierUpdate.setPhone(requestPhone);

        Supplier updatedSupplier = supplierRepo.saveAndFlush(supplierUpdate);
        return new UpdateResponseDto(updatedSupplier.getVersion(), ResponseMessage.UPDATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto delete(String id) {
        UUID validId = validateUUID(id);
        Supplier supplier = supplierRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        if (moveInRepo.existsBySupplierId(validId)) {
            throw new ResourceInUseException("Unable to delete, already referenced in move in records");
        }

        supplierRepo.deleteById(supplier.getId());
        return new DeleteResponseDto(ResponseMessage.DELETED.getMessage());
    }

}
