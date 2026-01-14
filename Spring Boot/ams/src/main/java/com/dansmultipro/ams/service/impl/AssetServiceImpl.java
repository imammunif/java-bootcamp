package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.AssetDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.asset.AssetRequestDto;
import com.dansmultipro.ams.dto.asset.AssetResponseDto;
import com.dansmultipro.ams.dto.asset.UpdateAssetRequestDto;
import com.dansmultipro.ams.model.Asset;
import com.dansmultipro.ams.service.AssetService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetDao assetDao;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AssetServiceImpl(AssetDao assetDao) {
        this.assetDao = assetDao;
    }

    @Override
    public List<AssetResponseDto> getAll() {
        List<AssetResponseDto> result = assetDao.getAll().stream()
                .map(v -> new AssetResponseDto(v.getId(),
                        v.getName(), v.getAssetCategory().getName(),
                        v.getAssetStatus().getName(), v.getCompany()
                        .getName(), v.getExpiredDate(), v.getCode()))
                .toList();
        return result;
    }

    @Override
    public AssetResponseDto getById(UUID id) {
        Asset asset = assetDao.getById(id).orElseThrow(
                () -> new RuntimeException("Asset not found")
        );
        return new AssetResponseDto(asset.getId(),
                asset.getName(), asset.getAssetCategory().getName(),
                asset.getAssetStatus().getName(), asset.getCompany()
                .getName(), asset.getExpiredDate(), asset.getCode());
    }

    @Transactional
    @Override
    public CreateResponseDto insert(AssetRequestDto data) {
        Asset assetInsert = new Asset();
        assetInsert.setId(UUID.randomUUID());
        assetInsert.setCreatedBy(UUID.randomUUID().toString());
        assetInsert.setCreatedAt(LocalDateTime.now());
        assetInsert.setName(data.getName());
        assetInsert.setName(data.getCategoryName());
        assetInsert.setName(data.getCompanyName());
        assetInsert.setName(data.getStatusName());

        Asset asset = assetDao.insert(assetInsert);

        return new CreateResponseDto(asset.getId(), "Saved");
    }

    @Transactional
    @Override
    public UpdateResponseDto update(UUID id, UpdateAssetRequestDto data) {
        Asset assetUpdate = assetDao.getById(id).orElseThrow(
                () -> new RuntimeException("Asset not found")
        );
        assetUpdate.setName(data.getStatusName());
        LocalDate expiredDate = LocalDate.parse(data.getExpiredDate(), formatter);
        assetUpdate.setExpiredDate(expiredDate);
        assetUpdate.setUpdatedBy(UUID.randomUUID().toString());
        assetUpdate.setUpdatedAt(LocalDateTime.now());

        assetDao.update(assetUpdate);

        return new UpdateResponseDto(assetUpdate.getVersion(), "Updated");
    }

    @Transactional
    @Override
    public DeleteResponseDto deleteById(UUID id) {
        Asset asset = assetDao.getById(id).orElseThrow(
                () -> new RuntimeException("Asset not found")
        );

        assetDao.deleteById(asset.getId());

        return new DeleteResponseDto("Deleted");
    }
}
