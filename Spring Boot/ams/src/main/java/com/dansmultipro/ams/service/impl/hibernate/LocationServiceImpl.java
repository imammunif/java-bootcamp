package com.dansmultipro.ams.service.impl.hibernate;

import com.dansmultipro.ams.dao.LocationDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Location;
import com.dansmultipro.ams.service.LocationService;
import com.dansmultipro.ams.service.impl.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class LocationServiceImpl extends BaseService implements LocationService {

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
    public LocationResponseDto getById(String id) {
        Location location = locationDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Location not found")
        );
        return new LocationResponseDto(location.getId(), location.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(LocationRequestDto data) {
        Location locationNew = new Location();
        Location locationInsert = prepareForInsert(locationNew);
        locationInsert.setName(data.getName());

        Location location = locationDao.insert(locationInsert);

        return new CreateResponseDto(location.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateLocationRequestDto data) {
        Location location = locationDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Location not found")
        );
        Location locationUpdate = prepareForUpdate(location);
        locationUpdate.setName(data.getName());

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
