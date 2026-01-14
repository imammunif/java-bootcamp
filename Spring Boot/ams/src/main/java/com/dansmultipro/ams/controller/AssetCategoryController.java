package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.asset.AssetCategoryResponseDto;
import com.dansmultipro.ams.service.AssetCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asset-categories")
public class AssetCategoryController {

    private final AssetCategoryService assetCategoryService;

    public AssetCategoryController(AssetCategoryService assetCategoryService) {
        this.assetCategoryService = assetCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<AssetCategoryResponseDto>> getAllAssetCategories() {
        List<AssetCategoryResponseDto> res = assetCategoryService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssetCategoryResponseDto> getAssetCategoryById(@PathVariable String id) {
        AssetCategoryResponseDto res = assetCategoryService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
