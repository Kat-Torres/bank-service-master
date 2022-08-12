package com.nttdata.bankservice.controller;

import com.nttdata.bankservice.model.Person;
import com.nttdata.bankservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public Mono<Person> register(@RequestBody Person person){ return personService.save(person);}

    @GetMapping
    public Flux<Person> getAll (){return personService.getAll();}

    @GetMapping(path = { "{id}" }, produces = { "application/json" })
    public Mono<Person> findById(@PathVariable("id")Long id ){return personService.findById(id);}

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    public Mono<ResponseEntity<Person>> update(@PathVariable("id")Long id, @RequestBody Person person ) {
        return personService.update(id, person)
                .map(r -> ResponseEntity.noContent().<Person>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());};

    @DeleteMapping(path = { "{id}" })
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id")Long id) {
        return personService.deleteById(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());}
}
