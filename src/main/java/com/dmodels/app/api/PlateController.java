package com.dmodels.app.api;

import com.dmodels.app.orders.model.Material;
import com.dmodels.app.orders.model.Plate;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.service.PlateService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/plates")
@RequiredArgsConstructor
public class PlateController {

    private final PlateService plateService;

    @GetMapping
    public List<PlateResponse> findAll() {
        return plateService.findAll()
                .stream()
                .map(PlateResponse::fromPlate)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<PlateResponse> findByRole(@PathVariable UUID id){

        return plateService.findById(id).stream().map(PlateResponse::fromPlate).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlateResponse createPlate(@RequestBody @Valid CreatePlateRequest request){
        final Plate plate = plateService.newPlate(request.getPrinterId());
        return PlateResponse.fromPlate(plate);
    }

    @Data
    @Builder
    static class PlateResponse {

        private UUID id;
        private Material material;
        private String filling;
        private String resolution;
        private Collection<Printout> printouts;

        static PlateResponse fromPlate(Plate plate) {
            return PlateResponse.builder()
                    .id(plate.getId())
                    .material(plate.getPrintouts().iterator().next().getMaterial())
                    .filling(plate.getPrintouts().iterator().next().getFilling())
                    .resolution(plate.getPrintouts().iterator().next().getResolution())
                    .printouts(new ArrayList<>(plate.getPrintouts())).build();
        }

    }

    @Data
    static class CreatePlateRequest {

        @NotNull
        private UUID printerId;

        UUID getPrinterId(){
            return printerId;
        }

    }
}
