package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.exception.DataMissMatchException;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Location;
import com.dansmultipro.ams.repository.LocationRepo;
import com.dansmultipro.ams.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl extends BaseService implements LocationService {

    private final LocationRepo locationRepo;

    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public List<LocationResponseDto> getAll() {
        List<LocationResponseDto> result = locationRepo.findAll().stream()
                .map(v -> new LocationResponseDto(v.getId(), v.getName()))
                .toList();
        return result;
    }

    @Override
    public LocationResponseDto getById(String id) {
        Location location = locationRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        return new LocationResponseDto(location.getId(), location.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(LocationRequestDto request) {
        Location locationNew = new Location();
        Location locationInsert = prepareForInsert(locationNew);
        locationInsert.setName(request.getName());
        Location location = locationRepo.save(locationInsert);
        return new CreateResponseDto(location.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateLocationRequestDto request) {
        Location location = locationRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Location not found")
        );
        if (!location.getVersion().equals(request.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Location locationUpdate = prepareForUpdate(location);
        locationUpdate.setName(request.getName());
        locationRepo.saveAndFlush(locationUpdate);
        return new UpdateResponseDto(locationUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Location location = locationRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        locationRepo.deleteById(location.getId());
        return new DeleteResponseDto("Deleted");
    }

}
