package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.AssetCategoryDao;
import com.dansmultipro.ams.model.AssetCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssetCategoryDaoImpl implements AssetCategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AssetCategory> getAll() {
        String hql = "SELECT ac FROM AssetCategory ac";
        List<AssetCategory> result = em.createQuery(hql, AssetCategory.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<AssetCategory> getById(UUID id) {
        AssetCategory assetCategory = em.find(AssetCategory.class, id);
        return Optional.ofNullable(assetCategory);
    }

}
