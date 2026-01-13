package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.LocationDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.model.Location;
import com.dansmultipro.ams.service.LocationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public List<LocationResponseDto> getAll() {
        List<LocationResponseDto> result = locationDao.getAll().stream()
                .map(v -> new LocationResponseDto(v.getId(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public LocationResponseDto getById(UUID id) {
        Location location = locationDao.getById(id).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        return new LocationResponseDto(id, location.getName());
    }

    @Override
    public CreateResponseDto insert(LocationRequestDto data) {
        Location locationInsert = new Location();
        locationInsert.setId(UUID.randomUUID());
        locationInsert.setCreatedBy(UUID.randomUUID().toString());
        locationInsert.setCreatedAt(LocalDateTime.now());
        locationInsert.setName(data.getName());

        Location location = locationDao.insert(locationInsert);

        return new CreateResponseDto(location.getId(), "Saved");
    }

    @Override
    public UpdateResponseDto update(UpdateLocationRequestDto data) {
        return null;
    }

    @Override
    public DeleteResponseDto deleteById(UUID id) {
        return null;
    }
}
