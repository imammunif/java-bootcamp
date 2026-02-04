package com.dansmultipro.ops.repository;

import com.dansmultipro.ops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> findAllByUserRole_Id(UUID roleId);

    Optional<User> findByUserRole_Code(String code);

}
