package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import com.dansmultipro.ams.dto.company.UpdateCompanyRequestDto;
import com.dansmultipro.ams.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
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
    public ResponseEntity<CreateResponseDto> createCompany(@RequestBody CompanyRequestDto company) {
        CreateResponseDto res = companyService.insert(company);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateCompany(@PathVariable String id, @RequestBody UpdateCompanyRequestDto company) {
        UpdateResponseDto res = companyService.update(id, company);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteCompany(@PathVariable String id) {
        DeleteResponseDto res = companyService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
