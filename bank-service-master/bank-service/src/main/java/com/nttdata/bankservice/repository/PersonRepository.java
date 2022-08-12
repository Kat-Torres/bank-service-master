package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, Long> {
}
