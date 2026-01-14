package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.AssetCategoryDao;
import com.dansmultipro.ams.dto.asset.AssetCategoryResponseDto;
import com.dansmultipro.ams.model.AssetCategory;
import com.dansmultipro.ams.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {

    private final AssetCategoryDao assetCategoryDao;

    @Autowired
    public AssetCategoryServiceImpl(AssetCategoryDao assetCategoryDao) {
        this.assetCategoryDao = assetCategoryDao;
    }

    @Override
    public List<AssetCategoryResponseDto> getAll() {
        List<AssetCategoryResponseDto> result = assetCategoryDao.getAll().stream()
                .map(v -> new AssetCategoryResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public AssetCategoryResponseDto getById(String id) {
        AssetCategory assetCategory = assetCategoryDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        return new AssetCategoryResponseDto(assetCategory.getId(), assetCategory.getName(), assetCategory.getCode());
    }
}
