package com.dmodels.app.api;


import com.dmodels.app.orders.model.Material;
import com.dmodels.app.orders.model.Printout;
import com.dmodels.app.orders.service.PrintoutService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/printouts")
@RequiredArgsConstructor
public class PrintoutRestController {

    private final PrintoutService printoutService;

    @GetMapping
    public List<PrintoutRestController.PrintoutResponse> findAll(@PathVariable UUID printoutId) {
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
        private String resolution;
        private Boolean permission;


        static PrintoutResponse fromPrintout(Printout printout) {
            return PrintoutResponse.builder()
                    .id(printout.getId())
                    .material(printout.getMaterial())
                    .filling(printout.getFilling())
                    .resolution(printout.getResolution())
                    .permission(printout.getPermission())
                    .build();


        }
    }

    @Data
    static class CreatePrintoutRequest {


        private Material material;
        private String filling;
        private String resolution;
        @NotNull
        private Boolean permission;




        Printout toPrintout() {
            Printout printout = new Printout(
                    this.material,
                    this.filling,
                    this.resolution,
                    this.permission
            );

            return printout;

        }
    }

}