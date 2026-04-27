package com.dansmultipro.ops.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private UUID id;
    private String name;
    private String email;
    private String roleName;
    private String isActive;
    private String version;

}
