package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.movein.CreateMoveInRequestDto;
import com.dansmultipro.ims.dto.movein.MoveInResponseDto;
import com.dansmultipro.ims.dto.moveindetail.MoveInDetailResponseDto;
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
    public ResponseEntity<PaginatedResponseDto<MoveInResponseDto>> getAllMoveIn(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponseDto<MoveInResponseDto> res = moveInService.getAll(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<MoveInDetailResponseDto>> getMoveInById(@PathVariable String id) {
        List<MoveInDetailResponseDto> res = moveInService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createMoveIn(@RequestBody @Valid CreateMoveInRequestDto requestDto) {
        CreateResponseDto res = moveInService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
