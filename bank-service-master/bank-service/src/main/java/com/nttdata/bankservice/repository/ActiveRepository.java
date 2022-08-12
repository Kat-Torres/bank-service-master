package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.model.Active;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveRepository extends ReactiveMongoRepository<Active, Long> {
}
