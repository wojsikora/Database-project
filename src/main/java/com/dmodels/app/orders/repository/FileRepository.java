
package com.dmodels.app.orders.repository;

import com.dmodels.app.orders.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    File findFileByName(String id);
}