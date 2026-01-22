package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CommonResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;
import com.dansmultipro.tms.service.PicCustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pic-customers")
public class PicCustomerController {

    private final PicCustomerService picCustomerService;

    public PicCustomerController(PicCustomerService picCustomerService) {
        this.picCustomerService = picCustomerService;
    }

    @GetMapping
    public ResponseEntity<List<PicCustomerResponseDto>> getAllPicCustomers() {
        List<PicCustomerResponseDto> res = picCustomerService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PicCustomerResponseDto> getPicCustomerById(@PathVariable String id) {
        PicCustomerResponseDto res = picCustomerService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommonResponseDto> createPicCustomer(@RequestBody @Valid CreatePicCustomerRequestDto requestDto) {
        CommonResponseDto res = picCustomerService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponseDto> updatePicCustomer(@PathVariable String id, @RequestBody @Valid UpdatePicCustomerRequestDto requestDto) {
        CommonResponseDto res = picCustomerService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
