package com.dansmultipro.ams.service.impl.hibernate;

import com.dansmultipro.ams.dao.AssetStatusDao;
import com.dansmultipro.ams.dto.asset.AssetStatusResponseDto;
import com.dansmultipro.ams.model.AssetStatus;
import com.dansmultipro.ams.service.AssetStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class AssetStatusServiceImpl implements AssetStatusService {

    private final AssetStatusDao assetStatusDao;

    @Autowired
    public AssetStatusServiceImpl(AssetStatusDao assetStatusDao) {
        this.assetStatusDao = assetStatusDao;
    }

    @Override
    public List<AssetStatusResponseDto> getAll() {
        List<AssetStatusResponseDto> result = assetStatusDao.getAll().stream()
                .map(v -> new AssetStatusResponseDto(v.getId(), v.getName(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public AssetStatusResponseDto getById(String id) {
        AssetStatus assetStatus = assetStatusDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Status not found")
        );
        return new AssetStatusResponseDto(assetStatus.getId(), assetStatus.getName(), assetStatus.getCode());
    }

}
