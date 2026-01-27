package com.dansmultipro.ims.dto.moveout;

import com.dansmultipro.ims.dto.moveoutdetail.CreateMoveOutDetailRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateMoveOutRequestDto {

    @NotBlank(message = "Agent is required")
    private String agentId;

    @NotEmpty(message = "Move out details cannot be empty")
    private List<@Valid CreateMoveOutDetailRequestDto> moveOutDetailList;

    public String getAgentId() {
        return agentId;
    }

    public List<CreateMoveOutDetailRequestDto> getMoveOutDetailList() {
        return moveOutDetailList;
    }

}
