package com.dansmultipro.tms.service;


import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.user.CreateUserRequestDto;
import com.dansmultipro.tms.dto.user.UpdateUserRequestDto;
import com.dansmultipro.tms.dto.user.UserResponseDto;
import com.dansmultipro.tms.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    List<UserResponseDto> getAll();

    UserResponseDto getById(String id);

    CreateResponseDto create(CreateUserRequestDto data);

    UpdateResponseDto update(String id, UpdateUserRequestDto data);

    DeleteResponseDto deleteById(String id);

}
