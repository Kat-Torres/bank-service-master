package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Product;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<Product> getAll();
    Mono<Product> save(Product product);
    Mono<Product> findById(Long id);
    Mono<ResponseEntity<Product>> update(Long id, Product product);
    Mono<Product> deleteById(Long id);
    Mono<Boolean> existsById(Long id);
}
