package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;
import com.dansmultipro.tms.service.PicCustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pic-customers")
public class PicCustomerController {

    private final PicCustomerService picCustomerService;

    public PicCustomerController(PicCustomerService picCustomerService) {
        this.picCustomerService = picCustomerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PicCustomerResponseDto> getPicCustomerById(@PathVariable String id) {
        PicCustomerResponseDto res = picCustomerService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createPicCustomer(@RequestBody @Valid CreatePicCustomerRequestDto requestDto) {
        CreateResponseDto res = picCustomerService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updatePicCustomer(@PathVariable String id, @RequestBody @Valid UpdatePicCustomerRequestDto requestDto) {
        UpdateResponseDto res = picCustomerService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
