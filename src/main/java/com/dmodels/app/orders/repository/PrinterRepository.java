package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PrinterRepository extends JpaRepository<Printer, UUID>{
    Optional<Printer> findById(UUID uuid);
}
