package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dao.LocationDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.model.Location;
import com.dansmultipro.ams.service.LocationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao;

    @PersistenceContext
    private EntityManager em;

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
    public LocationResponseDto getById(String id) {
        Location location = locationDao.getById(UUID.fromString(id)).orElseThrow(
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

        Location location = locationDao.insert(locationInsert);

        return new CreateResponseDto(location.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateLocationRequestDto data) {
        Location locationUpdate = locationDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        locationUpdate.setName(data.getName());
        locationUpdate.setUpdatedBy(UUID.randomUUID());
        locationUpdate.setUpdatedAt(LocalDateTime.now());

        locationDao.update(locationUpdate);
        em.flush();

        return new UpdateResponseDto(locationUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Location location = locationDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Location not found")
        );

        locationDao.deleteById(location.getId());

        return new DeleteResponseDto("Deleted");
    }

}
