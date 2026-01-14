package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.asset.AssetCategoryResponseDto;

import java.util.List;

public interface AssetCategoryService {

    List<AssetCategoryResponseDto> getAll();

    AssetCategoryResponseDto getById(String id);

}
