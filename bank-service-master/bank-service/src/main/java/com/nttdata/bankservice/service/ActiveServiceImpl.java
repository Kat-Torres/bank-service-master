package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Active;
import com.nttdata.bankservice.repository.ActiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ActiveServiceImpl implements ActiveService{


    @Autowired
    private ActiveRepository  activeRepository;

    @Override
    public Flux<Active> getAll() { return this.activeRepository.findAll(); }

    @Override
    public Mono<Active> save(Active active) { return this.activeRepository.save(active).log(); }

    @Override
    public Mono<Active> findById(Long id) { return this.activeRepository.findById(id); }

    @Override
    public Mono<ResponseEntity<Active>> update(Long id, Active active) {
        return activeRepository.findById(id)
                .flatMap(oldActive -> {
                    oldActive.setName(active.getName());
                    oldActive.setAvailableCredit(active.getAvailableCredit());
                    oldActive.setPersonId(active.getPersonId());
                    oldActive.setCreditLimited(active.getCreditLimited());
                    return activeRepository.save(oldActive);
                })
                .map(updateActive -> new ResponseEntity<>(updateActive, HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public Mono<Active> deleteById(Long id) { return activeRepository.findById(id)
            .flatMap(xRowDelete-> activeRepository.delete(xRowDelete)
                    .then(Mono.just(xRowDelete))); }

    @Override
    public Mono<Boolean> existsById(Long id) { return null; }
}
