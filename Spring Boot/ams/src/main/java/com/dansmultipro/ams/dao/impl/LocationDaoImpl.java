package com.dansmultipro.ams.dao.impl;

import com.dansmultipro.ams.dao.LocationDao;
import com.dansmultipro.ams.model.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LocationDaoImpl implements LocationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Location> getAll() {
        String hql = "SELECT l FROM Location l";
        List<Location> result = em.createQuery(hql, Location.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<Location> getById(UUID id) {
        Location location = em.find(Location.class, id);
        return Optional.ofNullable(location);
    }

    @Override
    public Location insert(Location location) {
        em.persist(location);
        return location;
    }

    @Override
    public Location update(Location location) {
        Location locationUpdate = em.merge(location);
        return locationUpdate;
    }

    @Override
    public void deleteById(UUID id) {
        Location location = em.find(Location.class, id);
        em.remove(location);
    }

}
