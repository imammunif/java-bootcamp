package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.CompanyDao;
import com.dansmultipro.ams.model.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Company> getAll() {
        String hql = "SELECT c FROM Company c";
        List<Company> result = em.createQuery(hql, Company.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Company> getById(UUID id) {
        Company company = em.find(Company.class, id);
        return Optional.ofNullable(company);
    }

    @Override
    public Company insert(Company company) {
        em.persist(company);
        return company;
    }

    @Override
    public Company update(Company company) {
        Company companyUpdate = em.merge(company);
        return companyUpdate;
    }

    @Override
    public void deleteById(UUID id) {
        Company company = em.find(Company.class, id);
        em.remove(company);
    }

}
