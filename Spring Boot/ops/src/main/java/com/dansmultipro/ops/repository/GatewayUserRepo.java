package com.dansmultipro.ops.repository;

import com.dansmultipro.ops.model.GatewayUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GatewayUserRepo extends JpaRepository<GatewayUser, UUID> {
}
