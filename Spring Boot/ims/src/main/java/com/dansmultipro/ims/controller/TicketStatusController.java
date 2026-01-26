package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.historytype.HistoryTypeResponseDto;
import com.dansmultipro.ims.service.HistoryTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("history-types")
public class TicketStatusController {

    private final HistoryTypeService historyTypeService;

    public TicketStatusController(HistoryTypeService historyTypeService) {
        this.historyTypeService = historyTypeService;
    }

    @GetMapping
    public ResponseEntity<List<HistoryTypeResponseDto>> getAllHistoryTypes() {
        List<HistoryTypeResponseDto> res = historyTypeService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<HistoryTypeResponseDto> getHistoryTypeById(@PathVariable String id) {
        HistoryTypeResponseDto res = historyTypeService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
