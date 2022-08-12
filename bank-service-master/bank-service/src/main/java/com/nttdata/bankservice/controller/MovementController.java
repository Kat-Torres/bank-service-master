package com.nttdata.bankservice.controller;

import com.nttdata.bankservice.model.Movement;
import com.nttdata.bankservice.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public Mono<Movement> register(@RequestBody Movement movement){return movementService.save(movement);}

    @GetMapping
    public Flux<Movement> getAll(){return movementService.getAll();}

    @GetMapping(path={"{id}"}, produces = {"application/json"})
    public Mono<Movement> findById(@PathVariable("id")Long id){return movementService.findById(id);}

    @PutMapping(path = {"{id}"}, produces = {"application/json"})
    public Mono<ResponseEntity<Movement>>update(@PathVariable("id")Long id, @RequestBody Movement movement){
        return movementService.update(id,movement)
                .map(r->ResponseEntity.noContent().<Movement>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());}
    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")Long id){
        return movementService.deleteById(id)
                .map(r-> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build()); }
}
