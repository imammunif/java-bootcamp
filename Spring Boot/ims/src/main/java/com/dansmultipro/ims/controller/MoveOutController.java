package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.moveout.CreateMoveOutRequestDto;
import com.dansmultipro.ims.dto.moveout.MoveOutResponseDto;
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
    public ResponseEntity<List<MoveOutResponseDto>> getAllMoveOut() {
        List<MoveOutResponseDto> res = moveOutService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MoveOutResponseDto> getMoveOutById(@PathVariable String id) {
        MoveOutResponseDto res = moveOutService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createMoveOut(@RequestBody @Valid CreateMoveOutRequestDto requestDto) {
        CreateResponseDto res = moveOutService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
