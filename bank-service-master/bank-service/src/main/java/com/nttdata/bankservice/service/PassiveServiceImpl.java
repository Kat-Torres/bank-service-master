package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Passive;
import com.nttdata.bankservice.repository.PassiveRepository;
import com.nttdata.bankservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PassiveServiceImpl implements PassiveService{

     @Autowired
     private PassiveRepository passiveRepository;
     private PersonRepository personRepository;
     @Override
     public Flux<Passive> getAll() {return this.passiveRepository.findAll(); }

     @Override
     public Mono<Passive> save(Passive passive) {

         return this.passiveRepository.save(passive).log();
     }

     @Override
     public Mono<Passive> findById(Long id) {return this.passiveRepository.findById(id); }

     @Override
     public Mono<ResponseEntity<Passive>> update(Long id, Passive passive) {
        return passiveRepository.findById(id)
                .flatMap(oldPassive->{
                    oldPassive.setAccountNumber(passive.getAccountNumber());
                    oldPassive.setAmount(passive.getAmount());
                    oldPassive.setPersonId((passive.getPersonId()));
                    return passiveRepository.save(oldPassive);
                })
                .map(updatePassive-> new ResponseEntity<>(updatePassive, HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));}

    @Override
     public Mono<Passive> deleteById(Long id) {
        return passiveRepository.findById(id)
                .flatMap(xRowDelete->passiveRepository.delete(xRowDelete)
                        .then(Mono.just(xRowDelete)));}
    @Override
    public Mono<Boolean> existsById(Long id) {
        return null;
    }
}
