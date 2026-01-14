package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.asset.AssetStatusResponseDto;

import java.util.List;

public interface AssetStatusService {

    List<AssetStatusResponseDto> getAll();

    AssetStatusResponseDto getById(String id);

}
