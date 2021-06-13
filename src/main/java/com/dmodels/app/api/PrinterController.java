package com.dmodels.app.api;

import com.dmodels.app.orders.model.MaterialCategory;
import com.dmodels.app.orders.model.Printer;
import com.dmodels.app.orders.model.Vector3D;
import com.dmodels.app.orders.service.PrinterService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/printers")
@RequiredArgsConstructor
public class PrinterController {

    private final PrinterService printerService;

    @GetMapping
    public List<PrinterResponse> findAll() {
        return printerService.findAll()
                .stream()
                .map(PrinterResponse::fromPrinter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/dimensions")
    public Double[] findDimensionById(@PathVariable UUID id){
        return printerService.findDimensionById(id).toArray();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrinterResponse createPrinter(@RequestBody @Valid CreatePrinterRequest request){
        final Printer printer = printerService.addPrinter(request.toPrinter());
        return PrinterResponse.fromPrinter(printer);
    }

    @Data
    @Builder
    static class PrinterResponse {

        private UUID id;
        private String name;
        private Double[] dimensions;
        private Collection<MaterialCategory> materials;

        static PrinterResponse fromPrinter(Printer printer) {
            return PrinterResponse.builder()
                    .id(printer.getId())
                    .name(printer.getName())
                    .dimensions(printer.getPlateDimensions().toArray())
                    .materials(printer.getMaterials()).build();
        }

    }

    @Data
    static class CreatePrinterRequest {

        @NotNull
        private String name;

        @NotNull
        private Double[] dimensions;

        @NotNull
        private Collection<String> materials;


        Printer toPrinter() {
            System.out.println(Arrays.toString(this.dimensions));
            System.out.println(this.materials);

            return new Printer(
                    this.name,
                    new Vector3D(this.dimensions[0], this.dimensions[1], this.dimensions[2]),
                    (Collection<MaterialCategory>) this.materials.stream().map(MaterialCategory::valueOf).collect(Collectors.toList()));
        }

    }
}
