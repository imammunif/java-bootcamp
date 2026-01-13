package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    @GetMapping
    public List<LocationResponseDto> getAllLocations() {
        return null;
    }

    @GetMapping("/{id}")
    public LocationResponseDto getLocationById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponseDto createLocation(@RequestBody LocationRequestDto location) {
        return null;
    }

    @PutMapping("/{id}")
    public UpdateResponseDto updateLocation(@PathVariable String id, @RequestBody LocationRequestDto location) {
        return null;
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDto deleteLocation(@PathVariable String id) {
        return null;
    }

}
