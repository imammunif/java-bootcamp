package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.moveout.CreateMoveOutRequestDto;
import com.dansmultipro.ims.dto.moveout.MoveOutResponseDto;
import com.dansmultipro.ims.dto.moveoutdetail.MoveOutDetailResponseDto;
import com.dansmultipro.ims.service.MoveOutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("move-out")
public class MoveOutController {

    private final MoveOutService moveOutService;

    public MoveOutController(MoveOutService moveOutService) {
        this.moveOutService = moveOutService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<MoveOutResponseDto>> getAllMoveOut(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponseDto<MoveOutResponseDto> res = moveOutService.getAll(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<MoveOutDetailResponseDto>> getMoveOutById(@PathVariable String id) {
        List<MoveOutDetailResponseDto> res = moveOutService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createMoveOut(@RequestBody @Valid CreateMoveOutRequestDto requestDto) {
        CreateResponseDto res = moveOutService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
