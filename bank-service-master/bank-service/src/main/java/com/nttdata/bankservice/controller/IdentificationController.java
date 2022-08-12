package com.nttdata.bankservice.controller;

import com.nttdata.bankservice.model.Identification;
import com.nttdata.bankservice.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/identifications")
public class IdentificationController {

    @Autowired
    private IdentificationService identificationService;

    @PostMapping
    public Mono<Identification> register(@RequestBody Identification identification) {
        return identificationService.save(identification);}

    @GetMapping
    public Flux<Identification> getAll() { return identificationService.getAll();}

    @GetMapping(path = {"{id}"},produces = {"application/json"})
    public Mono<Identification> findById(@PathVariable("id")Long id){return identificationService.findById(id);}

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    public Mono<ResponseEntity<Identification>>update(@PathVariable("id")Long id, @RequestBody Identification identification){
        return identificationService.update(id, identification)
                .map(r -> ResponseEntity.noContent().<Identification>build())
                .defaultIfEmpty(ResponseEntity.notFound().build()); };

    @DeleteMapping
    public Mono<ResponseEntity<Void>>deleteById(@PathVariable("id")Long id){
        return identificationService.deleteById(id)
                .map(r-> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build()); }
}