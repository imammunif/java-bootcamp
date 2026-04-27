package com.dansmultipro.ops.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductResponseDto {

    private UUID id;
    private String name;
    private String code;
    private String version;

}
