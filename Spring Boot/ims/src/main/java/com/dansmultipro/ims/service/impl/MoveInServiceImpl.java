package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.MoveIn;
import com.dansmultipro.ims.model.Supplier;
import com.dansmultipro.ims.repo.MoveInRepo;
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
    private final SupplierRepo supplierRepo;

    public MoveInServiceImpl(MoveInRepo moveInRepo, SupplierRepo supplierRepo) {
        this.moveInRepo = moveInRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public List<MoveInResponseDto> getAll() {
        List<MoveInResponseDto> result = moveInRepo.findAll().stream()
                .map(v -> new MoveInResponseDto(v.getId(), v.getCode(), v.getDate().toString(), v.getSupplier().getName()))
                .toList();
        return result;
    }

    @Override
    public MoveInResponseDto getById(String id) {
        MoveIn moveIn = moveInRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Move in supply not found")
        );
        return new MoveInResponseDto(moveIn.getId(), moveIn.getCode(), moveIn.getDate().toString(), moveIn.getSupplier().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateMoveInRequestDto requestDto) {
        Supplier supplier = supplierRepo.findById(UUID.fromString(requestDto.getProductId())).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        );
        MoveIn moveInNew = new MoveIn();
        MoveIn moveInInsert = prepareForInsert(moveInNew);
        moveInInsert.setCode(RandomGenerator.randomizeCode(20));
        moveInInsert.setDate(LocalDate.now());
        moveInInsert.setSupplier(supplier);
        MoveIn createdMoveIn = moveInRepo.save(moveInInsert);

        //TODO ADD NEW MOVE IN DETAIL

        return new CreateResponseDto(createdMoveIn.getId(), "Saved");
    }
}
