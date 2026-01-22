package com.dansmultipro.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PicCustomerRepo extends JpaRepository<PicCustomerRepo, UUID> {
}
