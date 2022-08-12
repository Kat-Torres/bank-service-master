package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Active;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActiveService {
    Flux<Active> getAll();
    Mono<Active> save(Active active);
    Mono<Active> findById(Long id);
    Mono<ResponseEntity<Active>> update(Long id, Active active);
    Mono<Active> deleteById(Long id);
    Mono<Boolean> existsById(Long id);

}
