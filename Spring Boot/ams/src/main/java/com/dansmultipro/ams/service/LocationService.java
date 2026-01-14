package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.location.LocationRequestDto;
import com.dansmultipro.ams.dto.location.LocationResponseDto;
import com.dansmultipro.ams.dto.location.UpdateLocationRequestDto;

import java.util.List;

public interface LocationService {

    List<LocationResponseDto> getAll();

    LocationResponseDto getById(String id);

    CreateResponseDto insert(LocationRequestDto data);

    UpdateResponseDto update(String id, UpdateLocationRequestDto data);

    DeleteResponseDto deleteById(String id);

}
