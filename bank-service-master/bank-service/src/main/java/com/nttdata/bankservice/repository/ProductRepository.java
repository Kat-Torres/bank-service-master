package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends ReactiveMongoRepository<Product, Long> {
}
