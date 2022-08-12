package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Person;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Flux<Person> getAll();
    Mono<Person> save(Person person);
    Mono<Person> findById(Long id);
    Mono<ResponseEntity<Person>> update(Long id, Person person);
    Mono<Person> deleteById(Long id);
    Mono<Boolean> existsById(Long id);
   }
