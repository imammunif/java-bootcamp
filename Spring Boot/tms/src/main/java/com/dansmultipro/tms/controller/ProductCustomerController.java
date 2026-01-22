package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CommonResponseDto;
import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;
import com.dansmultipro.tms.service.ProductCustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-customers")
public class ProductCustomerController {

    private final ProductCustomerService productCustomerService;

    public ProductCustomerController(ProductCustomerService productCustomerService) {
        this.productCustomerService = productCustomerService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCustomerResponseDto>> getAllProductCustomer() {
        List<ProductCustomerResponseDto> res = productCustomerService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductCustomerResponseDto> getProductCustomerById(@PathVariable String id) {
        ProductCustomerResponseDto res = productCustomerService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommonResponseDto> createProductCustomer(@RequestBody @Valid CreateProductCustomerRequestDto requestDto) {
        CommonResponseDto res = productCustomerService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommonResponseDto> updateProductCustomer(@PathVariable String id, @RequestBody @Valid UpdateProductCustomerRequestDto requestDto) {
        CommonResponseDto res = productCustomerService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
