package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.AssetStatusDao;
import com.dansmultipro.ams.model.AssetStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssetStatusDaoImpl implements AssetStatusDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AssetStatus> getAll() {
        String hql = "SELECT ast FROM AssetStatus ast";
        List<AssetStatus> result = em.createQuery(hql, AssetStatus.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<AssetStatus> getById(UUID id) {
        AssetStatus assetStatus = em.find(AssetStatus.class, id);
        return Optional.ofNullable(assetStatus);
    }

}
