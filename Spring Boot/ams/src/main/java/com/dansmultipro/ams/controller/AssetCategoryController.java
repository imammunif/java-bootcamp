package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.assetCategory.AssetCategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asset-categories")
public class AssetCategoryController {

    @GetMapping
    public List<AssetCategoryResponse> getAllAssetCategories() {
        return null;
    }

    @GetMapping("{id}")
    public AssetCategoryResponse getAssetCategoryById(@PathVariable String id) {
        return null;
    }

}
