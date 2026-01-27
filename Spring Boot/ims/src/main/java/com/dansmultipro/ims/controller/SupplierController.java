package com.dansmultipro.ims.controller;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.supplier.CreateSupplierRequestDto;
import com.dansmultipro.ims.dto.supplier.SupplierResponseDto;
import com.dansmultipro.ims.dto.supplier.UpdateSupplierRequestDto;
import com.dansmultipro.ims.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<SupplierResponseDto>> getAllSuppliers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PaginatedResponseDto<SupplierResponseDto> res = supplierService.getAll(page, size);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SupplierResponseDto> getSupplierById(@PathVariable String id) {
        SupplierResponseDto res = supplierService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createSupplier(@RequestBody @Valid CreateSupplierRequestDto requestDto) {
        CreateResponseDto res = supplierService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateSupplier(@PathVariable String id, @RequestBody @Valid UpdateSupplierRequestDto requestDto) {
        UpdateResponseDto res = supplierService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteSupplier(@PathVariable String id) {
        DeleteResponseDto res = supplierService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
