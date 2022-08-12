package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Identification;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IdentificationService {
    Flux<Identification> getAll();
    Mono<Identification> save(Identification identification);
    Mono<Identification> findById(Long id);
    Mono<ResponseEntity<Identification>> update(Long id, Identification identification);
    Mono<Identification> deleteById(Long id);
    Mono<Boolean> existsById(Long id);

}
