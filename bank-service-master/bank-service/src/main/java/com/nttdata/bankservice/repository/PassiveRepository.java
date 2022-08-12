package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.model.Passive;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassiveRepository extends ReactiveMongoRepository<Passive, Long> {
}
