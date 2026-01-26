package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveout.CreateMoveOutRequestDto;
import com.dansmultipro.ims.dto.moveout.MoveOutResponseDto;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.Agent;
import com.dansmultipro.ims.model.MoveOut;
import com.dansmultipro.ims.repo.AgentRepo;
import com.dansmultipro.ims.repo.MoveOutRepo;
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
    private final AgentRepo agentRepo;

    public MoveOutServiceImpl(MoveOutRepo moveOutRepo, AgentRepo agentRepo) {
        this.moveOutRepo = moveOutRepo;
        this.agentRepo = agentRepo;
    }

    @Override
    public List<MoveOutResponseDto> getAll() {
        List<MoveOutResponseDto> result = moveOutRepo.findAll().stream()
                .map(v -> new MoveOutResponseDto(v.getId(), v.getCode(), v.getDate().toString(), v.getAgent().getName()))
                .toList();
        return result;
    }

    @Override
    public MoveOutResponseDto getById(String id) {
        MoveOut moveOut = moveOutRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Move out checkout not found")
        );
        return new MoveOutResponseDto(moveOut.getId(), moveOut.getCode(), moveOut.getDate().toString(), moveOut.getAgent().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateMoveOutRequestDto requestDto) {
        Agent agent = agentRepo.findById(UUID.fromString(requestDto.getProductId())).orElseThrow(
                () -> new NotFoundException("Agent not found")
        );
        MoveOut moveOutNew = new MoveOut();
        MoveOut moveOutInsert = prepareForInsert(moveOutNew);
        moveOutInsert.setCode(RandomGenerator.randomizeCode(20));
        moveOutInsert.setDate(LocalDate.now());
        moveOutInsert.setAgent(agent);
        MoveOut createdMoveOut = moveOutRepo.save(moveOutInsert);

        //TODO ADD NEW MOVE IN DETAIL

        return new CreateResponseDto(createdMoveOut.getId(), "Saved");
    }
}
