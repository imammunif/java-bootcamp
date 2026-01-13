package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.AssetDao;
import com.dansmultipro.ams.model.Asset;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssetDaoImpl implements AssetDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Asset> getAll() {
        String hql = "SELECT e FROM Asset e";
        List<Asset> result = em.createQuery(hql, Asset.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Asset> getById(UUID id) {
        Asset asset = em.find(Asset.class, id);
        return Optional.ofNullable(asset);
    }

    @Override
    public Asset insert(Asset asset) {
        em.persist(asset);
        return asset;
    }

    @Override
    public Asset update(Asset asset) {
        Asset assetUpdate = em.merge(asset);
        return assetUpdate;
    }

    @Override
    public void deleteById(UUID id) {
        Asset asset = em.find(Asset.class, id);
        em.remove(asset);
    }

}
