package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.repository.PrintoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrintoutService {
    private final PrintoutRepository printoutRepository;

    public List<Printout> findAll() {
        return printoutRepository.findAll();
    }

    public Optional<Printout> findPrintoutById(UUID id) {
        return printoutRepository.findById(id);
    }

    public Printout createOrUpdatePrintout(Printout printout) {
        return printoutRepository.save(printout);
    }
}