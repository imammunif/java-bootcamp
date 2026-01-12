package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponse;
import com.dansmultipro.ams.dto.DeleteResponse;
import com.dansmultipro.ams.dto.UpdateResponse;
import com.dansmultipro.ams.dto.asset.AssetRequestDto;
import com.dansmultipro.ams.dto.asset.AssetResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class AssetController {

    @GetMapping("{companyId}/assets")
    public List<AssetResponseDto> getAllAssets(@PathVariable String companyId) {
        return null;
    }

    @GetMapping("{companyId}/assets/{id}")
    public AssetResponseDto getAssetById(@PathVariable String companyId, @PathVariable String id) {
        return null;
    }

    @PostMapping("{companyId}/assets")
    public CreateResponse createAsset(@PathVariable String companyId, @RequestBody AssetRequestDto asset) {
        return null;
    }

    @PutMapping("{companyId}/assets/{id}")
    public UpdateResponse updateAsset(@PathVariable String companyId, @PathVariable String id, @RequestBody AssetRequestDto asset) {
        return null;
    }

    @DeleteMapping("{companyId}/assets/{id}")
    public DeleteResponse deleteAsset(@PathVariable String companyId, @PathVariable String id) {
        return null;
    }

}
