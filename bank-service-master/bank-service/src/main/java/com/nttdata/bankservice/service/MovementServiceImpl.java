package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Movement;
import com.nttdata.bankservice.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MovementServiceImpl implements MovementService{

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public Flux<Movement> getAll() { return this.movementRepository.findAll(); }

    @Override
    public Mono<Movement> save(Movement movement) { return this.movementRepository.save(movement).log();}

    @Override
    public Mono<Movement> findById(Long id) {return this.movementRepository.findById(id);}

    @Override
    public Mono<ResponseEntity<Movement>> update(Long id, Movement movement) {
        return movementRepository.findById(id)
                .flatMap(oldMovement -> {
                    oldMovement.setProductType(movement.getProductType());
                    oldMovement.setAmount(movement.getAmount());
                    oldMovement.setDate(movement.getDate());
                    return movementRepository.save(oldMovement);
                })
                .map(updateMovement-> new ResponseEntity<>(updateMovement, HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public Mono<Movement> deleteById(Long id) {
        return movementRepository.findById(id)
                .flatMap(xRowDelete-> movementRepository.delete(xRowDelete)
                .then(Mono.just(xRowDelete)));
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return null;
    }
}
