package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.repository.PrintoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintoutService {
    private final PrintoutRepository printoutRepository;

    public List<Printout> findAll() {
        return printoutRepository.findAll();
    }

    public Printout findPrintoutById(long id) {
        return printoutRepository.findPrintoutById(id);
    }

    public Printout createOrUpdatePrintout(Printout printout) {
        return printoutRepository.save(printout);
    }
}