package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.supplier.CreateSupplierRequestDto;
import com.dansmultipro.ims.dto.supplier.SupplierResponseDto;
import com.dansmultipro.ims.dto.supplier.UpdateSupplierRequestDto;
import com.dansmultipro.ims.exception.DataMissMatchException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.Supplier;
import com.dansmultipro.ims.repo.SupplierRepo;
import com.dansmultipro.ims.service.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierServiceImpl extends BaseService implements SupplierService {

    private final SupplierRepo supplierRepo;

    public SupplierServiceImpl(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    @Override
    public List<SupplierResponseDto> getAll() {
        List<SupplierResponseDto> result = supplierRepo.findAll().stream()
                .map(v -> new SupplierResponseDto(v.getId(), v.getName(), v.getVersion().toString()))
                .toList();
        return result;
    }

    @Override
    public SupplierResponseDto getById(String id) {
        Supplier supplier = supplierRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Supplier not found")
        );
        return new SupplierResponseDto(supplier.getId(), supplier.getName(), supplier.getVersion().toString());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateSupplierRequestDto requestDto) {
        Supplier supplierNew = new Supplier();
        Supplier supplierInsert = prepareForInsert(supplierNew);
        supplierInsert.setName(requestDto.getName());
        Supplier createdSupplier = supplierRepo.save(supplierInsert);
        return new CreateResponseDto(createdSupplier.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateSupplierRequestDto requestDto) {
        Supplier supplier = supplierRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        if (!supplier.getVersion().equals(requestDto.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Supplier supplierUpdate = prepareForUpdate(supplier);
        supplierUpdate.setName(requestDto.getName());
        Supplier updatedSupplier = supplierRepo.saveAndFlush(supplierUpdate);
        return new UpdateResponseDto(updatedSupplier.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto delete(String id) {
        Supplier supplier = supplierRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Supplier not found")
        );
        supplierRepo.deleteById(supplier.getId());
        return new DeleteResponseDto("Deleted");
    }

}
