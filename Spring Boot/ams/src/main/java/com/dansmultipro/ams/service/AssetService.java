package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.asset.AssetRequestDto;
import com.dansmultipro.ams.dto.asset.AssetResponseDto;
import com.dansmultipro.ams.dto.asset.UpdateAssetRequestDto;

import java.util.List;
import java.util.UUID;

public interface AssetService {

    List<AssetResponseDto> getAll();

    AssetResponseDto getById(String id);

    CreateResponseDto insert(AssetRequestDto data);

    UpdateResponseDto update(String id, UpdateAssetRequestDto data);

    DeleteResponseDto deleteById(String id);

}
