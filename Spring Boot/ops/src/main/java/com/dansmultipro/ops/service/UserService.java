package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CommonResponseDto;
import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.DeleteResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.user.CreateUserCustomerRequestDto;
import com.dansmultipro.ops.dto.user.CreateUserGatewayRequestDto;
import com.dansmultipro.ops.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ops.dto.user.UserResponseDto;
import com.dansmultipro.ops.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    List<UserResponseDto> getAll();

    UserResponseDto getById(String id);

    CreateResponseDto createUserCustomer(CreateUserCustomerRequestDto data);

    CreateResponseDto createUserGateway(CreateUserGatewayRequestDto data);

    CommonResponseDto activateUserCustomer(String email, String code);

    UpdateResponseDto update(String id, UpdateUserRequestDto data);

    DeleteResponseDto deleteById(String id);

}
