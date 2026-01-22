package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.product.CreateProductRequestDto;
import com.dansmultipro.tms.dto.product.ProductResponseDto;
import com.dansmultipro.tms.dto.product.UpdateProductRequestDto;
import com.dansmultipro.tms.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> res = productService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable String id) {
        ProductResponseDto res = productService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createProduct(@RequestBody @Valid CreateProductRequestDto requestDto) {
        CreateResponseDto res = productService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateProduct(@PathVariable String id, @RequestBody @Valid UpdateProductRequestDto requestDto) {
        UpdateResponseDto res = productService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteProduct(@PathVariable String id) {
        DeleteResponseDto res = productService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
