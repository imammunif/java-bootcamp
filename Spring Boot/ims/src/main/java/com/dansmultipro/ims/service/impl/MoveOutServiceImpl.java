package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.constant.HistoryTypeCode;
import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveout.CreateMoveOutRequestDto;
import com.dansmultipro.ims.dto.moveout.MoveOutResponseDto;
import com.dansmultipro.ims.dto.moveoutdetail.CreateMoveOutDetailRequestDto;
import com.dansmultipro.ims.dto.moveoutdetail.MoveOutDetailResponseDto;
import com.dansmultipro.ims.exception.InvalidQuantityException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.*;
import com.dansmultipro.ims.repo.*;
import com.dansmultipro.ims.service.MoveOutService;
import com.dansmultipro.ims.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MoveOutServiceImpl extends BaseService implements MoveOutService {

    private final MoveOutRepo moveOutRepo;
    private final MoveOutDetailRepo moveOutDetailRepo;
    private final AgentRepo agentRepo;
    private final ProductRepo productRepo;
    private final HistoryTypeRepo historyTypeRepo;
    private final MoveHistoryRepo moveHistoryRepo;

    public MoveOutServiceImpl(MoveOutRepo moveOutRepo, MoveOutDetailRepo moveOutDetailRepo, AgentRepo agentRepo, ProductRepo productRepo, HistoryTypeRepo historyTypeRepo, MoveHistoryRepo moveHistoryRepo) {
        this.moveOutRepo = moveOutRepo;
        this.moveOutDetailRepo = moveOutDetailRepo;
        this.agentRepo = agentRepo;
        this.productRepo = productRepo;
        this.historyTypeRepo = historyTypeRepo;
        this.moveHistoryRepo = moveHistoryRepo;
    }

    @Override
    public List<MoveOutResponseDto> getAll() {
        List<MoveOutResponseDto> result = moveOutRepo.findAll().stream()
                .map(v -> new MoveOutResponseDto(v.getId(), v.getCode(), v.getDate().toString(), v.getAgent().getName()))
                .toList();
        return result;
    }

    @Override
    public List<MoveOutDetailResponseDto> getById(String id) {
        moveOutRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Move out checkout not found")
        );
        List<MoveOutDetailResponseDto> result = moveOutDetailRepo.findByMoveOutId(UUID.fromString(id)).stream()
                .map(v -> new MoveOutDetailResponseDto(
                        v.getId(), v.getQuantity().toString(), v.getProduct().getName(),
                        v.getMoveOut().getCode()))
                .toList();
        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateMoveOutRequestDto requestDto) {
        Agent agent = agentRepo.findById(UUID.fromString(requestDto.getAgentId())).orElseThrow(
                () -> new NotFoundException("Agent not found")
        );
        MoveOut moveOutNew = new MoveOut();
        MoveOut moveOutInsert = prepareForInsert(moveOutNew);
        moveOutInsert.setCode(RandomGenerator.randomizeCode(20));
        moveOutInsert.setDate(LocalDate.now());
        moveOutInsert.setAgent(agent);
        MoveOut createdMoveOut = moveOutRepo.save(moveOutInsert);

        List<CreateMoveOutDetailRequestDto> detailDtoList = requestDto.getMoveOutDetailList();
        for (CreateMoveOutDetailRequestDto detailDto : detailDtoList) {
            Product product = productRepo.findById(UUID.fromString(detailDto.getProductId())).orElseThrow(
                    () -> new NotFoundException("Product not found")
            );
            HistoryType type = historyTypeRepo.findByCode(HistoryTypeCode.OUT.name()).orElseThrow(
                    () -> new NotFoundException("History type not found")
            );
            Integer oldQty = product.getQuantity();
            Integer diffQty = detailDto.getQuantity();
            Integer newQty = oldQty - diffQty;
            if (diffQty > oldQty) {
                throw new InvalidQuantityException("Quantity is exceeding limit");
            }

            MoveOutDetail newMoveOutDetail = prepareForInsert(new MoveOutDetail());
            newMoveOutDetail.setMoveOut(createdMoveOut);
            newMoveOutDetail.setProduct(product);
            newMoveOutDetail.setQuantity(diffQty);
            moveOutDetailRepo.save(newMoveOutDetail);

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

        return new CreateResponseDto(createdMoveOut.getId(), "Saved");
    }
}
