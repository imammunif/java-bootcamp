package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.assetStatus.AssetStatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asset-status")
public class AssetStatusController {

    @GetMapping
    public List<AssetStatusResponse> getAllAssetStatus() {
        return null;
    }

    @GetMapping("{id}")
    public AssetStatusResponse getAssetStatusById(@PathVariable String id) {
        return null;
    }

}
