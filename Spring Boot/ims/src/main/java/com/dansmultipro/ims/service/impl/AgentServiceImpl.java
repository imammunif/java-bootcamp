package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.agent.AgentResponseDto;
import com.dansmultipro.ims.dto.agent.CreateAgentRequestDto;
import com.dansmultipro.ims.dto.agent.UpdateAgentRequestDto;
import com.dansmultipro.ims.exception.DataMissMatchException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.Agent;
import com.dansmultipro.ims.repo.AgentRepo;
import com.dansmultipro.ims.service.AgentService;
import jakarta.transaction.Transactional;
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
    public List<AgentResponseDto> getAll() {
        List<AgentResponseDto> result = agentRepo.findAll().stream()
                .map(v -> new AgentResponseDto(v.getId(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public AgentResponseDto getById(String id) {
        Agent agent = agentRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Agent not found")
        );
        return new AgentResponseDto(agent.getId(), agent.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateAgentRequestDto requestDto) {
        Agent agentNew = new Agent();
        Agent agentInsert = prepareForInsert(agentNew);
        agentInsert.setName(requestDto.getName());
        Agent createdAgent = agentRepo.save(agentInsert);
        return new CreateResponseDto(createdAgent.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAgentRequestDto requestDto) {
        Agent agent = agentRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Agent not found")
        );
        if (!agent.getVersion().equals(requestDto.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Agent agentUpdate = prepareForUpdate(agent);
        agentUpdate.setName(requestDto.getName());
        Agent updatedAgent = agentRepo.saveAndFlush(agentUpdate);
        return new UpdateResponseDto(updatedAgent.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto delete(String id) {
        Agent agent = agentRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Agent not found")
        );
        agentRepo.deleteById(agent.getId());
        return new DeleteResponseDto("Deleted");
    }

}
