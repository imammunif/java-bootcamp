package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dto.asset.AssetCategoryResponseDto;
import com.dansmultipro.ams.model.AssetCategory;
import com.dansmultipro.ams.repository.AssetCategoryRepo;
import com.dansmultipro.ams.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {

    private final AssetCategoryRepo assetCategoryRepo;

    @Autowired
    public AssetCategoryServiceImpl(AssetCategoryRepo assetCategoryRepo) {
        this.assetCategoryRepo = assetCategoryRepo;
    }

    @Override
    public List<AssetCategoryResponseDto> getAll() {
        List<AssetCategoryResponseDto> result = assetCategoryRepo.findAll().stream()
                .map(v -> new AssetCategoryResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public AssetCategoryResponseDto getById(String id) {
        AssetCategory assetCategory = assetCategoryRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        return new AssetCategoryResponseDto(assetCategory.getId(), assetCategory.getName(), assetCategory.getCode());
    }
}
