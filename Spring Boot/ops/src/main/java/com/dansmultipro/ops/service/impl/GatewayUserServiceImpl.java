package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.dto.gatewayuser.GatewayUserResponseDto;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.repository.GatewayRepo;
import com.dansmultipro.ops.repository.GatewayUserRepo;
import com.dansmultipro.ops.service.GatewayUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GatewayUserServiceImpl extends BaseService implements GatewayUserService {

    private final GatewayUserRepo gatewayUserRepo;
    private final GatewayRepo gatewayRepo;

    public GatewayUserServiceImpl(GatewayUserRepo gatewayUserRepo, GatewayRepo gatewayRepo) {
        this.gatewayUserRepo = gatewayUserRepo;
        this.gatewayRepo = gatewayRepo;
    }

    @Override
    public List<GatewayUserResponseDto> getAllByGatewayId(String gatewayId) {
        UUID validId = validateUUID(gatewayId);
        gatewayRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Gateway not found")
        );
        List<GatewayUserResponseDto> result = gatewayUserRepo.findByGatewayId(validId).stream()
                .map(v -> new GatewayUserResponseDto(
                        v.getId(), v.getUser().getName(), v.getUser().getEmail(), v.getGateway().getName())
                ).toList();
        return result;
    }

}
