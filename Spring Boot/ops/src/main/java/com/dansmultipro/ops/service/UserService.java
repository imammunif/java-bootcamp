package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.DeleteResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.user.CreateUserRequestDto;
import com.dansmultipro.ops.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ops.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAll();

    UserResponseDto getById(String id);

    CreateResponseDto create(CreateUserRequestDto requestDto);

    UpdateResponseDto update(String id, UpdateUserRequestDto requestDto);

    DeleteResponseDto deleteById(String id);

}
