package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveindetail.CreateMoveInDetailRequestDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;
import com.dansmultipro.ims.service.MoveInDetailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("move-in")
public class MoveInDetailController {

    private final MoveInDetailService moveInDetailService;

    public MoveInDetailController(MoveInDetailService moveInDetailService) {
        this.moveInDetailService = moveInDetailService;
    }

    @GetMapping("{movein_id}/details")
    public ResponseEntity<List<MoveInDetailResponseDto>> getAllDetailsById(@PathVariable("movein_id") String moveInId) {
        List<MoveInDetailResponseDto> res = moveInDetailService.getAllById(moveInId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("{movein_id}/details")
    public ResponseEntity<CreateResponseDto> createMoveInDetail(@PathVariable("movein_id") String moveInId, @RequestBody @Valid CreateMoveInDetailRequestDto requestDto) {
        CreateResponseDto res = moveInDetailService.create(moveInId, requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
