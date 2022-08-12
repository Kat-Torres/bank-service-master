package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.model.Identification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends ReactiveMongoRepository<Identification, Long> {
}
