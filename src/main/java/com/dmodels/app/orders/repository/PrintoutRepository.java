
package com.dmodels.app.orders.repository;


import com.dmodels.app.orders.model.Printout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PrintoutRepository  extends JpaRepository<Printout, UUID> {
    Optional<Printout> findById(UUID id);
}