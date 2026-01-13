package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao {

    List<Employee> getAll();

    Optional<Employee> getById(UUID id);

    Employee insert(Employee employee);

    Employee update(Employee employee);

    void deleteById(UUID id);

}
