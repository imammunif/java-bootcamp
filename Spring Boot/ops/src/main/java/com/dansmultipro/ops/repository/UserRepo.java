package com.dansmultipro.ops.repository;

import com.dansmultipro.ops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
}
