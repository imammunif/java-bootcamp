package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.productcategory.CreateProductCategoryRequestDto;
import com.dansmultipro.ims.dto.productcategory.ProductCategoryResponseDto;
import com.dansmultipro.ims.dto.productcategory.UpdateProductCategoryRequestDto;
import com.dansmultipro.ims.service.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ProductCategoryResponseDto>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponseDto<ProductCategoryResponseDto> res = productCategoryService.getAll(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductCategoryResponseDto> getCategoryById(@PathVariable String id) {
        ProductCategoryResponseDto res = productCategoryService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createCategory(@RequestBody @Valid CreateProductCategoryRequestDto requestDto) {
        CreateResponseDto res = productCategoryService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateCategory(@PathVariable String id, @RequestBody @Valid UpdateProductCategoryRequestDto requestDto) {
        UpdateResponseDto res = productCategoryService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteCategory(@PathVariable String id) {
        DeleteResponseDto res = productCategoryService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
