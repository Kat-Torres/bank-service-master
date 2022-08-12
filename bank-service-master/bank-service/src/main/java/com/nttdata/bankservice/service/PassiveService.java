package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Passive;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PassiveService {


    Flux<Passive> getAll();
    Mono<Passive> save(Passive passive);
    Mono<Passive> findById(Long id);
    Mono<ResponseEntity<Passive>> update(Long id, Passive passive);
    Mono<Passive> deleteById(Long id);
    Mono<Boolean> existsById(Long id);
}
