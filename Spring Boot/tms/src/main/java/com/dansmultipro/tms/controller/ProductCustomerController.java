package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;

import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;
import com.dansmultipro.tms.service.ProductCustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("product-customers")
public class ProductCustomerController {

    private final ProductCustomerService productCustomerService;

    public ProductCustomerController(ProductCustomerService productCustomerService) {
        this.productCustomerService = productCustomerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductCustomerResponseDto> getProductCustomerById(@PathVariable String id) {
        ProductCustomerResponseDto res = productCustomerService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createProductCustomer(@RequestBody @Valid CreateProductCustomerRequestDto requestDto) {
        CreateResponseDto res = productCustomerService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateProductCustomer(@PathVariable String id, @RequestBody @Valid UpdateProductCustomerRequestDto requestDto) {
        UpdateResponseDto res = productCustomerService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
