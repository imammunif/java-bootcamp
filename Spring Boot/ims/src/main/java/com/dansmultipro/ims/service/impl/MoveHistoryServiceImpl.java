package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.movehistory.MoveHistoryResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.MoveHistory;
import com.dansmultipro.ims.repo.MoveHistoryRepo;
import com.dansmultipro.ims.repo.ProductRepo;
import com.dansmultipro.ims.service.MoveHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MoveHistoryServiceImpl extends BaseService implements MoveHistoryService {

    private final MoveHistoryRepo moveHistoryRepo;
    private final ProductRepo productRepo;

    public MoveHistoryServiceImpl(MoveHistoryRepo moveHistoryRepo, ProductRepo productRepo) {
        this.moveHistoryRepo = moveHistoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public PaginatedResponseDto<MoveHistoryResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MoveHistory> historyPages = moveHistoryRepo.findAll(pageable);

        List<MoveHistory> historyList = historyPages.getContent();
        List<MoveHistoryResponseDto> responseDtoList = historyList.stream()
                .map(v -> new MoveHistoryResponseDto(
                        v.getId(), v.getDate().toString(), v.getQuantity().toString(),
                        v.getNewQuantity().toString(), v.getProduct().getName(),
                        v.getHistoryType().getName()))
                .toList();
        PaginatedResponseDto<MoveHistoryResponseDto> paginatedHistoryResponse = new PaginatedResponseDto<>(
                responseDtoList,
                historyPages.getTotalElements()
        );
        return paginatedHistoryResponse;
    }

    @Override
    public List<MoveHistoryResponseDto> getAllById(String productId) {
        UUID validId = validateUUID(productId);
        productRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        List<MoveHistoryResponseDto> result = moveHistoryRepo.findByProductId(validId).stream()
                .map(v -> new MoveHistoryResponseDto(
                        v.getId(), v.getDate().toString(), v.getQuantity().toString(),
                        v.getNewQuantity().toString(), v.getProduct().getName(),
                        v.getHistoryType().getName()))
                .toList();
        return result;
    }

}
