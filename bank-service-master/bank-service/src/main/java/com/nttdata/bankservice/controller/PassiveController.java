package com.nttdata.bankservice.controller;

import com.nttdata.bankservice.model.Passive;
import com.nttdata.bankservice.service.PassiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/passives")
@Validated
public class PassiveController {
    @Autowired
    private PassiveService passiveService;

    @PostMapping
    public Mono<Passive> register(@RequestBody Passive passive){return passiveService.save(passive);}

    @GetMapping
    public Flux<Passive> getAll() {return  passiveService.getAll();}

    @GetMapping(path = {"{id}"}, produces = {"application/json"})
    public Mono<Passive> findById(@PathVariable("id")Long id){return  passiveService.findById(id);}

    @PutMapping(path={"{id}"}, produces = {"application/json"})
    public Mono<ResponseEntity<Passive>> update(@PathVariable("id")Long id, @RequestBody Passive passive){
        return passiveService.update(id, passive)
                .map(r-> ResponseEntity.noContent().<Passive>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());};

    @DeleteMapping(path = {"{id}"})
    public Mono<ResponseEntity<Void>>deleteById(@PathVariable("id")Long id){
        return passiveService.deleteById(id)
                .map(r-> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());}
}
