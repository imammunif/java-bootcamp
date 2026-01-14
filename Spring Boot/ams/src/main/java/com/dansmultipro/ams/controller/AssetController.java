package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.asset.AssetRequestDto;
import com.dansmultipro.ams.dto.asset.AssetResponseDto;
import com.dansmultipro.ams.dto.asset.UpdateAssetRequestDto;
import com.dansmultipro.ams.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<AssetResponseDto>> getAllAssets() {
        List<AssetResponseDto> res = assetService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssetResponseDto> getAssetById(@PathVariable String id) {
        AssetResponseDto res = assetService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createAsset(@RequestBody AssetRequestDto asset) {
        CreateResponseDto res = assetService.insert(asset);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateAsset(@PathVariable String id, @RequestBody UpdateAssetRequestDto asset) {
        UpdateResponseDto res = assetService.update(id, asset);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteAsset(@PathVariable String id) {
        DeleteResponseDto res = assetService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
