package com.dansmultipro.tms.controller;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.company.CompanyResponseDto;
import com.dansmultipro.tms.dto.company.CreateCompanyRequestDto;
import com.dansmultipro.tms.dto.company.UpdateCompanyRequestDto;
import com.dansmultipro.tms.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> res = companyService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable String id) {
        CompanyResponseDto res = companyService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createCompany(@RequestBody @Valid CreateCompanyRequestDto requestDto) {
        CreateResponseDto res = companyService.create(requestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateCompany(@PathVariable String id, @RequestBody @Valid UpdateCompanyRequestDto requestDto) {
        UpdateResponseDto res = companyService.update(id, requestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteCompany(@PathVariable String id) {
        DeleteResponseDto res = companyService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
