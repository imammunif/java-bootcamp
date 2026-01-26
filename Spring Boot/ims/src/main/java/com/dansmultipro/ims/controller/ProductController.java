package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.product.CreateProductRequestDto;
import com.dansmultipro.ims.dto.product.CreateProductResponseDto;
import com.dansmultipro.ims.dto.product.UpdateProductRequestDto;
import com.dansmultipro.ims.service.ProductService;
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
    public ResponseEntity<List<CreateProductResponseDto>> getAllProducts() {
        List<CreateProductResponseDto> res = productService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CreateProductResponseDto> getProductById(@PathVariable String id) {
        CreateProductResponseDto res = productService.getById(id);
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
