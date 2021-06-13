package com.dmodels.app.orders.service;

import com.dmodels.app.orders.model.Material;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.model.Vector3D;
import com.dmodels.app.orders.repository.PrintoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public Collection<Printout> findByDimensionsLessThan(Vector3D dimensions){
        return printoutRepository.findByDimensionsLessThan(dimensions);
    }

    Collection<Printout> findByMaterialCategory(String materialCategory){
        return printoutRepository.findByMaterial_Category(materialCategory);
    }

    Collection<Printout> findByMaterialColor(String materialColor){
        return printoutRepository.findByMaterial_Color(materialColor);
    }

    Collection<Printout> findByMaterialPriceLessThan(Double price){
        return printoutRepository.findByMaterial_PriceLessThan(price);
    }

    Double getPrintoutValue(UUID id)
    {
        Printout printout = this.findPrintoutById(id).get();
        Double price = printout.getMaterial().getPrice();
        Double[] dimensions = printout.getDimensions().toArray();
        return dimensions[0]*dimensions[1]*dimensions[2]*price;


    }
}