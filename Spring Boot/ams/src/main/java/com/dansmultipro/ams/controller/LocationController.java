package com.dansmultipro.ams.controller;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationResponseDto>> getAllLocations() {
        List<LocationResponseDto> res = locationService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationResponseDto> getLocationById(@PathVariable String id) {
        LocationResponseDto res = locationService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> createLocation(@RequestBody @Valid LocationRequestDto location) {
        CreateResponseDto res = locationService.insert(location);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDto> updateLocation(@PathVariable String id, @RequestBody @Valid UpdateLocationRequestDto location) {
        UpdateResponseDto res = locationService.update(id, location);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDto> deleteLocation(@PathVariable String id) {
        DeleteResponseDto res = locationService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
