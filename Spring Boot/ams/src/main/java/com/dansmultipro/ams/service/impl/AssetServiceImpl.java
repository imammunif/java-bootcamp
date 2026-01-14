package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.AssetCategoryDao;
import com.dansmultipro.ams.dao.AssetDao;
import com.dansmultipro.ams.dao.AssetStatusDao;
import com.dansmultipro.ams.dao.CompanyDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.asset.AssetRequestDto;
import com.dansmultipro.ams.dto.asset.AssetResponseDto;
import com.dansmultipro.ams.dto.asset.UpdateAssetRequestDto;
import com.dansmultipro.ams.model.Asset;
import com.dansmultipro.ams.model.AssetCategory;
import com.dansmultipro.ams.model.AssetStatus;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.service.AssetService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    private final CompanyDao companyDao;
    private final AssetStatusDao assetStatusDao;
    private final AssetCategoryDao assetCategoryDao;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @PersistenceContext
    private EntityManager em;

    public AssetServiceImpl(AssetDao assetDao, CompanyDao companyDao, AssetStatusDao assetStatusDao, AssetCategoryDao assetCategoryDao) {
        this.assetDao = assetDao;
        this.companyDao = companyDao;
        this.assetStatusDao = assetStatusDao;
        this.assetCategoryDao = assetCategoryDao;
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
    public AssetResponseDto getById(String id) {
        Asset asset = assetDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Asset not found")
        );
        return new AssetResponseDto(asset.getId(),
                asset.getName(), asset.getAssetCategory().getName(),
                asset.getAssetStatus().getName(), asset.getCompany()
                .getName(), asset.getExpiredDate(), asset.getCode());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(AssetRequestDto data) {
        String assetCompanyId = data.getCompanyId();
        Company assetCompany = companyDao.getById(UUID.fromString(assetCompanyId)).orElseThrow(
                () -> new RuntimeException("Company not found")
        );
        String assetCategoryId = data.getCategoryId();
        AssetCategory assetCategory = assetCategoryDao.getById(UUID.fromString(assetCategoryId)).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        String assetStatusId = data.getStatusId();
        AssetStatus assetStatus = assetStatusDao.getById(UUID.fromString(assetStatusId)).orElseThrow(
                () -> new RuntimeException("Status not found")
        );

        Asset assetInsert = new Asset();
        assetInsert.setId(UUID.randomUUID());
        assetInsert.setCreatedBy(UUID.randomUUID().toString());
        assetInsert.setCreatedAt(LocalDateTime.now());
        assetInsert.setName(data.getName());
        assetInsert.setAssetCompany(assetCompany);
        assetInsert.setAssetCategory(assetCategory);
        assetInsert.setAssetStatus(assetStatus);
        if (data.getExpiredDate() != null) {
            LocalDate expiredDate = LocalDate.parse(data.getExpiredDate(), formatter);
            assetInsert.setExpiredDate(expiredDate);
        }
        assetInsert.setCode(data.getCode());

        Asset asset = assetDao.insert(assetInsert);

        return new CreateResponseDto(asset.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAssetRequestDto data) {
        Asset assetUpdate = assetDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Asset not found")
        );
        String assetStatusId = data.getStatusId();
        AssetStatus assetStatus = assetStatusDao.getById(UUID.fromString(assetStatusId)).orElseThrow(
                () -> new RuntimeException("Status not found")
        );
        assetUpdate.setAssetStatus(assetStatus);
        if (data.getExpiredDate() != null) {
            LocalDate expiredDate = LocalDate.parse(data.getExpiredDate(), formatter);
            assetUpdate.setExpiredDate(expiredDate);
        }
        assetUpdate.setUpdatedBy(UUID.randomUUID().toString());
        assetUpdate.setUpdatedAt(LocalDateTime.now());

        assetDao.update(assetUpdate);
        em.flush();

        return new UpdateResponseDto(assetUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Asset asset = assetDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Asset not found")
        );

        assetDao.deleteById(asset.getId());

        return new DeleteResponseDto("Deleted");
    }
}
