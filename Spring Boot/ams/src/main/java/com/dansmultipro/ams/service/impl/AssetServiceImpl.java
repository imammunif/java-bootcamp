package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.asset.AssetRequestDto;
import com.dansmultipro.ams.dto.asset.AssetResponseDto;
import com.dansmultipro.ams.dto.asset.UpdateAssetRequestDto;
import com.dansmultipro.ams.exception.DataIntegrityException;
import com.dansmultipro.ams.exception.DataMissMatchException;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Asset;
import com.dansmultipro.ams.model.AssetCategory;
import com.dansmultipro.ams.model.AssetStatus;
import com.dansmultipro.ams.model.Company;
import com.dansmultipro.ams.repository.AssetCategoryRepo;
import com.dansmultipro.ams.repository.AssetRepo;
import com.dansmultipro.ams.repository.AssetStatusRepo;
import com.dansmultipro.ams.repository.CompanyRepo;
import com.dansmultipro.ams.service.AssetService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AssetServiceImpl extends BaseService implements AssetService {

    private final AssetRepo assetRepo;
    private final CompanyRepo companyRepo;
    private final AssetStatusRepo assetStatusRepo;
    private final AssetCategoryRepo assetCategoryRepo;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AssetServiceImpl(AssetRepo assetRepo, CompanyRepo companyRepo, AssetStatusRepo assetStatusRepo, AssetCategoryRepo assetCategoryRepo) {
        this.assetRepo = assetRepo;
        this.companyRepo = companyRepo;
        this.assetStatusRepo = assetStatusRepo;
        this.assetCategoryRepo = assetCategoryRepo;
    }

    @Override
    @Cacheable(value = "assets", key = "'all'")
    public List<AssetResponseDto> getAll() {
        List<Asset> assetList = assetRepo.findAll();
        List<AssetResponseDto> assetResponseDtoList = new ArrayList<>();
        for (Asset asset : assetList) {
            AssetResponseDto responseDto = new AssetResponseDto(asset.getId().toString(),
                    asset.getName(), asset.getAssetCategory().getName(),
                    asset.getAssetStatus().getName(), asset.getCompany()
                    .getName(), asset.getExpiredDate() != null ? asset.getExpiredDate().toString() : "", asset.getCode());
            assetResponseDtoList.add(responseDto);
        }
        return assetResponseDtoList;
    }

    @Override
    @Cacheable(value = "assets", key = "#id")
    public AssetResponseDto getById(String id) {
        Asset asset = assetRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Asset not found")
        );
        return new AssetResponseDto(asset.getId().toString(),
                asset.getName(), asset.getAssetCategory().getName(),
                asset.getAssetStatus().getName(), asset.getCompany()
                .getName(), asset.getExpiredDate().toString(), asset.getCode());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    @CacheEvict(value = "assets", allEntries = true)
    public CreateResponseDto insert(AssetRequestDto request) {
        String assetCompanyId = request.getCompanyId();
        Company assetCompany = companyRepo.findById(UUID.fromString(assetCompanyId)).orElseThrow(
                () -> new NotFoundException("Company not found")
        );
        String assetCategoryId = request.getCategoryId();
        AssetCategory assetCategory = assetCategoryRepo.findById(UUID.fromString(assetCategoryId)).orElseThrow(
                () -> new NotFoundException("Category not found")
        );
        String assetStatusId = request.getStatusId();
        AssetStatus assetStatus = assetStatusRepo.findById(UUID.fromString(assetStatusId)).orElseThrow(
                () -> new NotFoundException("Status not found")
        );
        Asset assetNew = new Asset();
        Asset assetInsert = prepareForInsert(assetNew);

        assetInsert.setName(request.getName());
        assetInsert.setAssetCompany(assetCompany);
        assetInsert.setAssetCategory(assetCategory);
        assetInsert.setAssetStatus(assetStatus);
        if (request.getExpiredDate() != null) {
            LocalDate expiredDate = LocalDate.parse(request.getExpiredDate(), formatter);
            assetInsert.setExpiredDate(expiredDate);
        }
        String requestCode = request.getCode();
        if (assetRepo.findByCode(requestCode).isPresent()) {
            throw new DataIntegrityException("Code already exist");
        }
        assetInsert.setCode(requestCode);
        Asset asset = assetRepo.save(assetInsert);

        return new CreateResponseDto(asset.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateAssetRequestDto request) {
        Asset asset = assetRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Asset not found")
        );
        if (!asset.getVersion().equals(request.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        String assetStatusId = request.getStatusId();
        AssetStatus assetStatus = assetStatusRepo.findById(UUID.fromString(assetStatusId)).orElseThrow(
                () -> new NotFoundException("Status not found")
        );
        Asset assetUpdate = prepareForUpdate(asset);
        assetUpdate.setAssetStatus(assetStatus);
        if (request.getExpiredDate() != null) {
            LocalDate expiredDate = LocalDate.parse(request.getExpiredDate(), formatter);
            assetUpdate.setExpiredDate(expiredDate);
        }
        String requestCode = request.getCode();
        if (!asset.getCode().equals(request.getCode())) {
            if (assetRepo.findByCode(requestCode).isPresent()) {
                throw new DataIntegrityException("Code already exist");
            }
        }
        assetRepo.saveAndFlush(assetUpdate);

        return new UpdateResponseDto(assetUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Asset asset = assetRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Asset not found")
        );
        assetRepo.deleteById(asset.getId());

        return new DeleteResponseDto("Deleted");
    }
}
