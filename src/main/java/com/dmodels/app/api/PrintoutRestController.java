package com.dmodels.app.api;


import com.dmodels.app.orders.model.Material;
import com.dmodels.app.orders.model.MaterialCategory;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.model.Vector3D;
import com.dmodels.app.orders.service.PrintoutService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/printouts")
@RequiredArgsConstructor
public class PrintoutRestController {

    private final PrintoutService printoutService;

    @GetMapping
    public List<PrintoutRestController.PrintoutResponse> findAll() {
        return printoutService.findAll()
                .stream()
                .map(PrintoutRestController.PrintoutResponse::fromPrintout)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrintoutRestController.PrintoutResponse createPrintout(@RequestBody @Valid PrintoutRestController.CreatePrintoutRequest request) {
        final Printout printout = printoutService.createOrUpdatePrintout(request.toPrintout());
        return PrintoutRestController.PrintoutResponse.fromPrintout(printout);
    }

    @Data
    @Builder
    static class PrintoutResponse {

        private UUID id;
        private Material material;
        private String filling;
        private Boolean permission;
        private Double[] dimensions;


        static PrintoutResponse fromPrintout(Printout printout) {
            return PrintoutResponse.builder()
                    .id(printout.getId())
                    .material(printout.getMaterial())
                    .filling(printout.getFilling())
                    .permission(printout.getPermission())
                    .dimensions(printout.getDimensions().toArray())
                    .build();


        }
    }

    @Data
    static class CreatePrintoutRequest {


        private String category;
        private String color;
        private Double price;
        private String filling;
        @NotNull
        private Boolean permission;
        private Double[] dimensions;

        Printout toPrintout() {
            Material material = new Material(this.category, this.color, this.price);

            System.out.println(material);
            System.out.println(dimensions.length);

            return new Printout(
                    material,
                    this.filling,
                    this.permission,
                    new Vector3D(dimensions[0],dimensions[1], dimensions[2] )
            );

        }
    }

}