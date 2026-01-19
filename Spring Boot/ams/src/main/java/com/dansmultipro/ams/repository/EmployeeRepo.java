package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByCode(String code);

    Optional<Employee> findByPhone(String phone);

}
