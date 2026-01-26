package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;
import com.dansmultipro.ims.service.MoveInService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("move-in")
public class MoveInController {

    private final MoveInService moveInService;

    public MoveInController(MoveInService moveInService) {
        this.moveInService = moveInService;
    }

    @GetMapping
    public ResponseEntity<List<MoveInResponseDto>> getAllMoveIn() {
        List<MoveInResponseDto> res = moveInService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MoveInResponseDto> getMoveInById(@PathVariable String id) {
        MoveInResponseDto res = moveInService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createMoveIn(@RequestBody @Valid CreateMoveInRequestDto requestDto) {
        CreateResponseDto res = moveInService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
