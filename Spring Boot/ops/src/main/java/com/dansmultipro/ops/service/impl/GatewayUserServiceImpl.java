package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.dto.gatewayuser.GatewayUserResponseDto;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.GatewayUser;
import com.dansmultipro.ops.repository.GatewayUserRepo;
import com.dansmultipro.ops.service.GatewayUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GatewayUserServiceImpl extends BaseService implements GatewayUserService {

    private final GatewayUserRepo gatewayUserRepo;

    public GatewayUserServiceImpl(GatewayUserRepo gatewayUserRepo) {
        this.gatewayUserRepo = gatewayUserRepo;
    }

    @Override
    public List<GatewayUserResponseDto> getAllByGatewayId() {
        UUID userId = principalService.getPrincipal().getId();
        GatewayUser gatewayUser = gatewayUserRepo.findByUserId(userId).orElseThrow(
                () -> new NotFoundException("Gateway user not found")
        );

        List<GatewayUserResponseDto> result = gatewayUserRepo.findByGatewayId(gatewayUser.getGateway().getId()).stream()
                .map(v -> new GatewayUserResponseDto(
                        v.getId(), v.getUser().getName(), v.getUser().getEmail(), v.getGateway().getName())
                ).toList();
        return result;
    }

}
