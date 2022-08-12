package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Person;
import com.nttdata.bankservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Flux<Person> getAll() { return this.personRepository.findAll();}

    @Override
    public Mono<Person> save(Person person) { return this.personRepository.save(person).log();}


    @Override
    public Mono<Person> findById(Long id) { return this.personRepository.findById(id);}

    @Override
    public Mono<Boolean> existsById(Long id) { return this.personRepository.existsById(id);}

    @Override
    public Mono<ResponseEntity<Person>> update(Long id, Person person) {
        return personRepository.findById(id)
                .flatMap(oldPerson -> {
                    oldPerson.setDocumentId(person.getDocumentId());
                    oldPerson.setDocumentNumber(person.getDocumentNumber());
                    oldPerson.setName(person.getName());
                    oldPerson.setAddress(person.getAddress());
                    oldPerson.setPhone(person.getPhone());
                    oldPerson.setEmail(person.getEmail());
                    return personRepository.save(oldPerson);
                })
                .map(updatePerson -> new ResponseEntity<>(updatePerson, HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));}
    @Override
    public Mono<Person> deleteById(Long id){
        return personRepository.findById(id)
                .flatMap(xRowDelete -> personRepository.delete(xRowDelete)
                .then(Mono.just(xRowDelete)));}
}
