package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.movehistory.MoveHistoryResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.repo.MoveHistoryRepo;
import com.dansmultipro.ims.repo.ProductRepo;
import com.dansmultipro.ims.service.MoveHistoryService;

import java.util.List;
import java.util.UUID;

public class MoveHistoryServiceImpl extends BaseService implements MoveHistoryService {

    private final MoveHistoryRepo moveHistoryRepo;
    private final ProductRepo productRepo;

    public MoveHistoryServiceImpl(MoveHistoryRepo moveHistoryRepo, ProductRepo productRepo) {
        this.moveHistoryRepo = moveHistoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<MoveHistoryResponseDto> getAll() {
        List<MoveHistoryResponseDto> result = moveHistoryRepo.findAll().stream()
                .map(v -> new MoveHistoryResponseDto(
                        v.getId(), v.getDate().toString(), v.getQuantity().toString(),
                        v.getNewQuantity().toString(), v.getProduct().getName(),
                        v.getHistoryType().getName()))
                .toList();
        return result;
    }

    @Override
    public List<MoveHistoryResponseDto> getAllById(String productId) {
        productRepo.findById(UUID.fromString(productId)).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        List<MoveHistoryResponseDto> result = moveHistoryRepo.findByProductId(UUID.fromString(productId)).stream()
                .map(v -> new MoveHistoryResponseDto(
                        v.getId(), v.getDate().toString(), v.getQuantity().toString(),
                        v.getNewQuantity().toString(), v.getProduct().getName(),
                        v.getHistoryType().getName()))
                .toList();
        return result;
    }
    
}
