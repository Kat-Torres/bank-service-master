package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.model.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<Movement,Long> {
}
