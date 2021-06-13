
package com.dmodels.app.orders.repository;


import com.dmodels.app.orders.model.Material;
import com.dmodels.app.orders.model.MaterialCategory;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.model.Vector3D;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PrintoutRepository  extends JpaRepository<Printout, UUID> {
    Optional<Printout> findById(UUID id);

    Collection<Printout> findByDimensionsLessThan(Vector3D dimensions);

    Collection<Printout> findByMaterial_Category(String materialCategory);

    Collection<Printout> findByMaterial_Color(String materialColor);

    Collection<Printout> findByMaterial_PriceLessThan(Double price);
}