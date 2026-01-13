package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;
import com.dansmultipro.ams.model.Location;

import java.util.List;
import java.util.UUID;

public interface LocationService {

    List<LocationResponseDto> getAll();

    LocationResponseDto getById(UUID id);

    CreateResponseDto insert(LocationRequestDto data);

    UpdateResponseDto update(UUID id, UpdateLocationRequestDto data);

    DeleteResponseDto deleteById(UUID id);

}
