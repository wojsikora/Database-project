package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.Plate;
import com.dmodels.app.orders.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface PlateRepository extends JpaRepository<Plate, UUID> {
    Collection<Plate> findAllByGenerationDateEquals(Date generationDate);
    Collection<Plate> findAllByGenerationDateBetween(Date start, Date stop);
    Collection<Plate> findAllByPrinterEquals(Printer printer);
}
