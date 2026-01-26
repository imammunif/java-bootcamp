package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveoutdetail.CreateMoveOutDetailRequestDto;
import com.dansmultipro.ims.dto.moveoutdetail.MoveOutDetailResponseDto;
import com.dansmultipro.ims.service.MoveOutDetailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("move-out")
public class MoveOutDetailController {

    private final MoveOutDetailService moveOutDetailService;

    public MoveOutDetailController(MoveOutDetailService moveOutDetailService) {
        this.moveOutDetailService = moveOutDetailService;
    }

    @GetMapping("{moveout_id}/details")
    public ResponseEntity<List<MoveOutDetailResponseDto>> getAllDetailsById(@PathVariable("moveout_id") String moveInId) {
        List<MoveOutDetailResponseDto> res = moveOutDetailService.getAllById(moveInId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("{moveout_id}/details")
    public ResponseEntity<CreateResponseDto> createMoveInDetail(@PathVariable("moveout_id") String moveOutId, @RequestBody @Valid CreateMoveOutDetailRequestDto requestDto) {
        CreateResponseDto res = moveOutDetailService.create(moveOutId, requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}


