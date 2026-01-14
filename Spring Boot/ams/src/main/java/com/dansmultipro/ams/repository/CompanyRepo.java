package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepo extends JpaRepository<Company, UUID> {
}
