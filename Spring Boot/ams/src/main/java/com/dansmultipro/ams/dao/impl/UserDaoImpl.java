package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.UserDao;
import com.dansmultipro.ams.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        String hql = "SELECT u FROM User u";
        List<User> result = em.createQuery(hql, User.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<User> getById(UUID id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public User insert(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        User userUpdate = em.merge(user);
        return userUpdate;
    }

    @Override
    public void deleteById(UUID id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

}
