package com.dmodels.app.orders.repository;


import com.dmodels.app.orders.model.Printout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PrintoutRepository  extends JpaRepository<Printout, Long> {
    Printout findPrintoutById(long id);
}


