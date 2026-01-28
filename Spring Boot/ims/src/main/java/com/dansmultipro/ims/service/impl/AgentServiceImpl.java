package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.constant.ResponseMessage;
import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.agent.AgentResponseDto;
import com.dansmultipro.ims.dto.agent.CreateAgentRequestDto;
import com.dansmultipro.ims.dto.agent.UpdateAgentRequestDto;
import com.dansmultipro.ims.exception.DataIntegrityException;
import com.dansmultipro.ims.exception.DataMissMatchException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.Agent;
import com.dansmultipro.ims.repo.AgentRepo;
import com.dansmultipro.ims.service.AgentService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgentServiceImpl extends BaseService implements AgentService {

    private final AgentRepo agentRepo;

    public AgentServiceImpl(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }


    @Override
    public PaginatedResponseDto<AgentResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Agent> agentPages = agentRepo.findAll(pageable);

        List<Agent> agentList = agentPages.getContent();
        List<AgentResponseDto> responseDtoList = agentList.stream()
                .map(v -> new AgentResponseDto(v.getId(), v.getCode(), v.getName(), v.getAddress(), v.getPhone(), v.getVersion().toString()))
                .toList();

        PaginatedResponseDto<AgentResponseDto> paginatedAgentReponse = new PaginatedResponseDto<>(
                responseDtoList,
                agentPages.getTotalElements()
        );
        return paginatedAgentReponse;
    }

    @Override
    public AgentResponseDto getById(String id) {
        UUID validId = validateUUID(id);
        Agent agent = agentRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Agent not found")
        );
        return new AgentResponseDto(agent.getId(), agent.getCode(), agent.getName(), agent.getAddress(), agent.getPhone(), agent.getVersion().toString());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateAgentRequestDto requestDto) {
        String requestCode = requestDto.getCode();
        if (agentRepo.findByCodeIgnoreCase(requestCode).isPresent()) {
            throw new DataIntegrityException("Agent with corresponding code already exist");
        }
        String requestPhone = requestDto.getPhone();
        if (agentRepo.findByPhone(requestPhone).isPresent()) {
            throw new DataIntegrityException("Phone already exist");
        }
        Agent agentNew = new Agent();
        Agent agentInsert = prepareForInsert(agentNew);
        agentInsert.setCode(requestCode);
        agentInsert.setName(requestDto.getName());
        agentInsert.setAddress(requestDto.getAddress());
        agentInsert.setPhone(requestPhone);

        Agent createdAgent = agentRepo.save(agentInsert);
        return new CreateResponseDto(createdAgent.getId(), ResponseMessage.CREATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAgentRequestDto requestDto) {
        UUID validId = validateUUID(id);
        Agent agent = agentRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Agent not found")
        );
        if (!agent.getVersion().equals(requestDto.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        String requestCode = requestDto.getCode();
        if (!agent.getCode().equals(requestCode)) {
            if (agentRepo.findByCodeIgnoreCase(requestCode).isPresent()) {
                throw new DataIntegrityException("Code already exist");
            }
        }
        String requestPhone = requestDto.getPhone();
        if (!agent.getPhone().equals(requestPhone)) {
            if (agentRepo.findByPhone(requestPhone).isPresent()) {
                throw new DataIntegrityException("Phone already exist");
            }
        }
        Agent agentUpdate = prepareForUpdate(agent);
        agentUpdate.setCode(requestCode);
        agentUpdate.setName(requestDto.getName());
        agentUpdate.setAddress(requestDto.getAddress());
        agentUpdate.setPhone(requestPhone);

        Agent updatedAgent = agentRepo.saveAndFlush(agentUpdate);
        return new UpdateResponseDto(updatedAgent.getVersion(), ResponseMessage.UPDATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto delete(String id) {
        UUID validId = validateUUID(id);
        Agent agent = agentRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Agent not found")
        );

        agentRepo.deleteById(agent.getId());
        return new DeleteResponseDto(ResponseMessage.DELETED.getMessage());
    }

}
