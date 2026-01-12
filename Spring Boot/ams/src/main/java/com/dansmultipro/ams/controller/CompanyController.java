package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.company.CompanyRequest;
import com.dansmultipro.ams.dto.company.CompanyResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return null;
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createCompany(@RequestBody CompanyRequest company) {
        return null;
    }

    @PutMapping("/{id}")
    public UpdateResponse updateCompany(@PathVariable String id, @RequestBody CompanyRequest company) {
        return null;
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteCompany(@PathVariable String id) {
        return null;
    }

}
