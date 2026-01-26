package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveoutdetail.CreateMoveOutDetailRequestDto;
import com.dansmultipro.ims.dto.moveoutdetail.MoveOutDetailResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.MoveOut;
import com.dansmultipro.ims.model.MoveOutDetail;
import com.dansmultipro.ims.model.Product;
import com.dansmultipro.ims.repo.MoveOutDetailRepo;
import com.dansmultipro.ims.repo.MoveOutRepo;
import com.dansmultipro.ims.repo.ProductRepo;
import com.dansmultipro.ims.service.MoveOutDetailService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MoveOutDetailServiceImpl extends BaseService implements MoveOutDetailService {

    private final MoveOutRepo moveOutRepo;
    private final MoveOutDetailRepo moveOutDetailRepo;
    private final ProductRepo productRepo;

    public MoveOutDetailServiceImpl(MoveOutRepo moveOutRepo, MoveOutDetailRepo moveOutDetailRepo, ProductRepo productRepo) {
        this.moveOutRepo = moveOutRepo;
        this.moveOutDetailRepo = moveOutDetailRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<MoveOutDetailResponseDto> getAllById(String moveOutId) {
        moveOutRepo.findById(UUID.fromString(moveOutId)).orElseThrow(
                () -> new NotFoundException("Move out checkout not found")
        );
        List<MoveOutDetailResponseDto> result = moveOutDetailRepo.findByMoveOutId(UUID.fromString(moveOutId)).stream()
                .map(v -> new MoveOutDetailResponseDto(
                        v.getId(), v.getQuantity().toString(), v.getProduct().getName(),
                        v.getMoveOut().getCode()))
                .toList();
        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(String moveOutId, CreateMoveOutDetailRequestDto requestDto) {
        MoveOut moveOut = moveOutRepo.findById(UUID.fromString(moveOutId)).orElseThrow(
                () -> new NotFoundException("Move out checkout not found")
        );
        Product product = productRepo.findById(UUID.fromString(requestDto.getProductId())).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        MoveOutDetail newMoveOutDetail = prepareForInsert(new MoveOutDetail());
        newMoveOutDetail.setMoveOut(moveOut);
        newMoveOutDetail.setProduct(product);
        MoveOutDetail createdMoveOutDetail = moveOutDetailRepo.save(newMoveOutDetail);

        return new CreateResponseDto(createdMoveOutDetail.getId(), "Saved");
    }

}
