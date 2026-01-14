package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Employee> getAll() {
        String hql = "SELECT e FROM Employee e";
        List<Employee> result = em.createQuery(hql, Employee.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Employee> getById(UUID id) {
        Employee employee = em.find(Employee.class, id);
        return Optional.ofNullable(employee);
    }

    @Override
    public Employee insert(Employee employee) {
        em.persist(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Employee employeeUpdate = em.merge(employee);
        return employeeUpdate;
    }

    @Override
    public void deleteById(UUID id) {
        Employee employee = em.find(Employee.class, id);
        em.remove(employee);
    }

}
