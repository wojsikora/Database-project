package com.dmodels.app.controller;

import com.dmodels.app.orders.model.Printout;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dmodels.app.orders.repository.PrintoutRepository;

@RestController
@RequestMapping(value = "/printout", produces = "application/hal+json")
public class PrintoutRestController {

    final PrintoutRepository printoutRepository;


    public PrintoutRestController(PrintoutRepository printoutRepository) {
        this.printoutRepository = printoutRepository;
    }

    @GetMapping
    public ResponseEntity < CollectionModel<Printout> > all() {
        return ResponseEntity.ok(CollectionModel.of(printoutRepository.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Printout>> get(@PathVariable final long id) {
        return ResponseEntity.ok(EntityModel.of(printoutRepository.findPrintoutById(id)));
    }
    @PostMapping
    public ResponseEntity < EntityModel<Printout> > post(@RequestBody final Printout printoutFromRequest) {
        //final Printout printout = new Printout(printoutFromRequest);

    }
    @PutMapping("/{id}") public ResponseEntity<EntityModel<Printout>> put(@PathVariable("id") final long id, @RequestBody Printout personFromRequest) {
        // PUT
    }
    @DeleteMapping("/{id}") public ResponseEntity < EntityModel<Printout >> delete(@PathVariable("id") final long id) {
        // DELETE
    }
}
