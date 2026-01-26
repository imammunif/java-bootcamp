package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveindetail.CreateMoveInDetailRequestDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.MoveIn;
import com.dansmultipro.ims.model.MoveInDetail;
import com.dansmultipro.ims.model.Product;
import com.dansmultipro.ims.repo.MoveInDetailRepo;
import com.dansmultipro.ims.repo.MoveInRepo;
import com.dansmultipro.ims.repo.ProductRepo;
import com.dansmultipro.ims.service.MoveInDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MoveInDetailServiceImpl extends BaseService implements MoveInDetailService {

    private final MoveInRepo moveInRepo;
    private final MoveInDetailRepo moveInDetailRepo;
    private final ProductRepo productRepo;

    public MoveInDetailServiceImpl(MoveInRepo moveInRepo, MoveInDetailRepo moveInDetailRepo, ProductRepo productRepo) {
        this.moveInRepo = moveInRepo;
        this.moveInDetailRepo = moveInDetailRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<MoveInDetailResponseDto> getAllById(String moveInId) {
        moveInRepo.findById(UUID.fromString(moveInId)).orElseThrow(
                () -> new NotFoundException("Move in supply not found")
        );
        List<MoveInDetailResponseDto> result = moveInDetailRepo.findByMoveInId(UUID.fromString(moveInId)).stream()
                .map(v -> new MoveInDetailResponseDto(
                        v.getId(), v.getQuantity().toString(), v.getProduct().getName(),
                        v.getMoveIn().getCode()))
                .toList();
        return result;
    }

    @Override
    public CreateResponseDto create(String moveInId, CreateMoveInDetailRequestDto requestDto) {
        MoveIn moveIn = moveInRepo.findById(UUID.fromString(moveInId)).orElseThrow(
                () -> new NotFoundException("Move in supply not found")
        );
        Product product = productRepo.findById(UUID.fromString(requestDto.getProductId())).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        MoveInDetail newMoveInDetail = prepareForInsert(new MoveInDetail());
        newMoveInDetail.setMoveIn(moveIn);
        newMoveInDetail.setProduct(product);
        MoveInDetail createdMoveInDetail = moveInDetailRepo.save(newMoveInDetail);

        return new CreateResponseDto(createdMoveInDetail.getId(), "Saved");
    }

}
