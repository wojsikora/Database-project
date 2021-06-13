package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Printer;
import com.dmodels.app.orders.model.Vector3D;
import com.dmodels.app.orders.repository.PrinterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrinterService {
    private final PrinterRepository printerRepository;

    public Optional<Printer> findById(UUID id){
        return printerRepository.findById(id);
    }

    public Vector3D findDimensionById(UUID id){
        return Objects.requireNonNull(printerRepository.findById(id).orElse(null)).getPlateDimensions();
    }

    public Collection<Printer> findAll(){
        return printerRepository.findAll();
    }

    public Printer addPrinter(Printer printer){
        printerRepository.save(printer);
        return printer;
    }
}
