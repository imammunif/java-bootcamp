package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.movehistory.MoveHistoryResponseDto;
import com.dansmultipro.ims.service.MoveHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class MoveHistoryController {

    private final MoveHistoryService moveHistoryService;

    public MoveHistoryController(MoveHistoryService moveHistoryService) {
        this.moveHistoryService = moveHistoryService;
    }

    @GetMapping("/histories")
    public ResponseEntity<List<MoveHistoryResponseDto>> getAllHistories() {
        List<MoveHistoryResponseDto> res = moveHistoryService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{product_id}/histories")
    public ResponseEntity<List<MoveHistoryResponseDto>> getAllHistoryById(@PathVariable("product_id") String productId) {
        List<MoveHistoryResponseDto> res = moveHistoryService.getAllById(productId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
