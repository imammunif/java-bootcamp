package com.dansmultipro.tms.repository;

import com.dansmultipro.tms.model.PicCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PicCustomerRepo extends JpaRepository<PicCustomer, UUID> {
}
