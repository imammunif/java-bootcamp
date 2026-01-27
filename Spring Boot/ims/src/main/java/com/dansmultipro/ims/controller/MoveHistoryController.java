package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.movehistory.MoveHistoryResponseDto;
import com.dansmultipro.ims.service.MoveHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class MoveHistoryController {

    private final MoveHistoryService moveHistoryService;

    public MoveHistoryController(MoveHistoryService moveHistoryService) {
        this.moveHistoryService = moveHistoryService;
    }

    @GetMapping("/histories")
    public ResponseEntity<PaginatedResponseDto<MoveHistoryResponseDto>> getAllHistories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponseDto<MoveHistoryResponseDto> res = moveHistoryService.getAll(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}/histories")
    public ResponseEntity<List<MoveHistoryResponseDto>> getAllHistoryById(@PathVariable("id") String id) {
        List<MoveHistoryResponseDto> res = moveHistoryService.getAllById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
