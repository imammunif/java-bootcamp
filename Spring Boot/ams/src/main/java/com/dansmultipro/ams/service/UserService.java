package com.dansmultipro.ams.service;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponseDto> getAll();

    UserResponseDto getById(UUID id);

    CreateResponseDto insert(UserRequestDto data);

    UpdateResponseDto update(UpdateUserRequestDto data);

    DeleteResponseDto deleteById(UUID id);

}
