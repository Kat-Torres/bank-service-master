package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Movement;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {

    Flux<Movement> getAll();
    Mono<Movement> save(Movement movement);
    Mono<Movement> findById(Long id);
    Mono<ResponseEntity<Movement>> update(Long id, Movement movement);
    Mono<Movement> deleteById(Long id);
    Mono<Boolean> existsById(Long id);
}
