package com.nttdata.bankservice.controller;

import com.nttdata.bankservice.model.Active;
import com.nttdata.bankservice.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/actives")
public class ActiveController {
    @Autowired
    private ActiveService activeService;

    @PostMapping
    public Mono <Active> register(@RequestBody Active active){ return activeService.save(active);}

    @GetMapping
    public Flux<Active> getAll(){return activeService.getAll();}

    @GetMapping(path= {"{id}"}, produces = {"application/json"})
    public Mono<Active> findById(@PathVariable("id")Long id){return activeService.findById(id);}

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    public Mono<ResponseEntity<Active>> update(@PathVariable("id")Long id, @RequestBody Active active) {
        return activeService.update(id, active)
                .map(r -> ResponseEntity.noContent().<Active> build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    };

    @DeleteMapping(path = {"{id}"})
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")Long id){
    return activeService.deleteById(id)
        .map(r-> ResponseEntity.ok().<Void>build())
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}

