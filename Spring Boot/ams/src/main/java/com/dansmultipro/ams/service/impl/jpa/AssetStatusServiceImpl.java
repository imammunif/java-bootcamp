package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dto.asset.AssetStatusResponseDto;
import com.dansmultipro.ams.model.AssetStatus;
import com.dansmultipro.ams.repository.AssetStatusRepo;
import com.dansmultipro.ams.service.AssetStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class AssetStatusServiceImpl implements AssetStatusService {

    private final AssetStatusRepo assetStatusRepo;

    @Autowired
    public AssetStatusServiceImpl(AssetStatusRepo assetStatusRepo) {
        this.assetStatusRepo = assetStatusRepo;
    }

    @Override
    public List<AssetStatusResponseDto> getAll() {
        List<AssetStatusResponseDto> result = assetStatusRepo.findAll().stream()
                .map(v -> new AssetStatusResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public AssetStatusResponseDto getById(String id) {
        AssetStatus assetStatus = assetStatusRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Status not found")
        );
        return new AssetStatusResponseDto(assetStatus.getId(), assetStatus.getName(), assetStatus.getCode());
    }

}
