package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyDao {

    List<Company> getAll();

    Optional<Company> getById(UUID id);

    Company insert(Company company);

    Company update(Company company);

    void deleteById(UUID id);

}
