package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.company.CompanyRequestDto;
import com.dansmultipro.ams.dto.company.CompanyResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {

    @GetMapping
    public List<CompanyResponseDto> getAllCompanies() {
        return null;
    }

    @GetMapping("/{id}")
    public CompanyResponseDto getCompanyById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createCompany(@RequestBody CompanyRequestDto company) {
        return null;
    }

    @PutMapping("/{id}")
    public UpdateResponse updateCompany(@PathVariable String id, @RequestBody CompanyRequestDto company) {
        return null;
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteCompany(@PathVariable String id) {
        return null;
    }

}
