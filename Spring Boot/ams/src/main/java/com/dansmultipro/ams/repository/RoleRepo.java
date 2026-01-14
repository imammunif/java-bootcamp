package com.dansmultipro.ams.repository;

import com.dansmultipro.ams.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
}
