package com.dansmultipro.ams.dao;

import com.dansmultipro.ams.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    List<User> getAll();

    Optional<User> getById(UUID id);

    User insert(User user);

    User update(User user);

    void deleteById(UUID id);
    
}
