package com.dansmultipro.ims.service;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.agent.AgentResponseDto;
import com.dansmultipro.ims.dto.agent.CreateAgentRequestDto;
import com.dansmultipro.ims.dto.agent.UpdateAgentRequestDto;

import java.util.List;

public interface AgentService {

    List<AgentResponseDto> getAll();

    AgentResponseDto getById(String id);

    CreateResponseDto create(CreateAgentRequestDto requestDto);

    UpdateResponseDto update(String id, UpdateAgentRequestDto requestDto);

    DeleteResponseDto delete(String id);

}
