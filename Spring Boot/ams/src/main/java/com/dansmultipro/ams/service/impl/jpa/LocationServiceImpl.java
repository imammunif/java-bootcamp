package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.model.Location;
import com.dansmultipro.ams.repository.LocationRepo;
import com.dansmultipro.ams.service.LocationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepo locationRepo;

    @PersistenceContext
    private EntityManager em;

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
    public CreateResponseDto insert(LocationRequestDto data) {
        Location locationInsert = new Location();
        locationInsert.setId(UUID.randomUUID());
        locationInsert.setCreatedBy(UUID.randomUUID());
        locationInsert.setCreatedAt(LocalDateTime.now());
        locationInsert.setName(data.getName());

        Location location = locationRepo.save(locationInsert);

        return new CreateResponseDto(location.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateLocationRequestDto data) {
        Location locationUpdate = locationRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        locationUpdate.setName(data.getName());
        locationUpdate.setUpdatedBy(UUID.randomUUID());
        locationUpdate.setUpdatedAt(LocalDateTime.now());

        locationRepo.save(locationUpdate);
        em.flush();

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
