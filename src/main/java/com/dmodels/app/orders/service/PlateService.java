package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Order;
import com.dmodels.app.orders.model.Plate;
import com.dmodels.app.orders.model.Printer;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.repository.PlateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlateService {
    private final PlateRepository plateRepository;
    private final PrinterService printerService;
    private final OrderService orderService;
    private final PrintoutService printoutService;


    public Collection<Plate> findAll(){
        return plateRepository.findAll();
    }

    public Optional<Plate> findById(UUID id){
        return plateRepository.findById(id);
    }
    public Collection<Plate> findAllByGenerationDateEquals(Date generationDate){
        return plateRepository.findAllByGenerationDateEquals(generationDate);
    }
    public Collection<Plate> findAllByGenerationDateBetween(Date start, Date stop){
        return plateRepository.findAllByGenerationDateBetween(start, stop);
    }
    public Collection<Plate> findAllByPrinterEquals(Printer printer){
        return plateRepository.findAllByPrinterEquals(printer);
    }

    public Plate newPlate(UUID printerId){
        Order oldestOrder = orderService.getOldestOrder();
        Printout printout = (Printout) oldestOrder.getToPrinted().toArray()[0];
        oldestOrder.getToPrinted().remove(printout);
        oldestOrder.getAlreadyPrinted().add(printout);
        if (oldestOrder.getToPrinted().isEmpty()){
            oldestOrder.setImplementDate(new Date());
        }

        orderService.createOrUpdateOrder(oldestOrder);

        Set<Printout> printouts = new HashSet<>();
        printouts.add(printout);

        Printer printer = printerService.findById(printerId).orElse(null);

        Plate plate = new Plate(printer, printouts);

        plateRepository.save(plate);

        printout.getPlates().add(plate);

        printoutService.createOrUpdatePrintout(printout);

        return plate;
    }

}
