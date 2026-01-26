package com.dansmultipro.ims.dto.movein;

import com.dansmultipro.ims.dto.moveindetail.CreateMoveInDetailRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateMoveInRequestDto {

    @NotBlank(message = "Supplier is required")
    private String supplierId;

    @NotEmpty(message = "Move in details cannot be empty")
    private List<CreateMoveInDetailRequestDto> moveInDetailList;

    public String getSupplierId() {
        return supplierId;
    }

    public List<CreateMoveInDetailRequestDto> getMoveInDetailList() {
        return moveInDetailList;
    }

}
