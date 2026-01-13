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

    AssetResponseDto getById(UUID id);

    CreateResponseDto insert(AssetRequestDto data);

    UpdateResponseDto update(UUID id, UpdateAssetRequestDto data);

    DeleteResponseDto deleteById(UUID id);

}
