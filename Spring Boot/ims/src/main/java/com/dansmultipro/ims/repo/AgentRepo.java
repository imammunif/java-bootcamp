package com.dansmultipro.ims.repo;

import com.dansmultipro.ims.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AgentRepo extends JpaRepository<Agent, UUID> {

    Optional<Agent> findByCodeIgnoreCase(String code);

    Optional<Agent> findByPhone(String phone);

}
