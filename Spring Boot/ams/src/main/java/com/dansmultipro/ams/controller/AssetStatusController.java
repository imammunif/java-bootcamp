package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.asset.AssetStatusResponseDto;
import com.dansmultipro.ams.dto.role.RoleResponseDto;
import com.dansmultipro.ams.service.AssetStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asset-status")
public class AssetStatusController {

    private final AssetStatusService assetStatusService;

    public AssetStatusController(AssetStatusService assetStatusService) {
        this.assetStatusService = assetStatusService;
    }

    @GetMapping
    public ResponseEntity<List<AssetStatusResponseDto>> getAllAssetStatus() {
        List<AssetStatusResponseDto> res = assetStatusService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssetStatusResponseDto> getAssetStatusById(@PathVariable String id) {
        AssetStatusResponseDto res = assetStatusService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
