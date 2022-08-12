package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Identification;
import com.nttdata.bankservice.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IdentificationServiceImpl implements IdentificationService{

    @Autowired
    private IdentificationRepository identificationRepository;


    @Override
    public Flux<Identification> getAll() { return this.identificationRepository.findAll();}

    @Override
    public Mono<Identification> save(Identification identification) { return this.identificationRepository.save(identification).log(); }

    @Override
    public Mono<Identification> findById(Long id) { return this.identificationRepository.findById(id);}

    @Override
    public Mono<ResponseEntity<Identification>> update(Long id, Identification identification) {
        return identificationRepository.findById(id)
                .flatMap(oldIdentification -> {
                    oldIdentification.setName( identification.getName());
                    return identificationRepository.save(oldIdentification);
                })
                .map(updateIdentification -> new ResponseEntity<>(updateIdentification, HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public Mono<Identification> deleteById(Long id) {
        return identificationRepository.findById(id)
                .flatMap(xRowDelete-> identificationRepository.delete(xRowDelete)
                        .then(Mono.just(xRowDelete)));
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return null;
    }
}
