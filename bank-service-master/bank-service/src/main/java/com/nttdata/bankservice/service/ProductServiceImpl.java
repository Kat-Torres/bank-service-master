package com.nttdata.bankservice.service;

import com.nttdata.bankservice.model.Product;
import com.nttdata.bankservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Mono<Product> save(Product product) {
        return this.productRepository.save(product).log();
    }

    @Override
    public Mono<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Mono<ResponseEntity<Product>> update(Long id, Product product) {
        return productRepository.findById(id)
                .flatMap(oldProduct -> {
                    oldProduct.setProductType(product.getProductType());
                    oldProduct.setDescription(product.getDescription());
                    oldProduct.setMonthlyCommision(product.getMonthlyCommision());
                    oldProduct.setLimitedMovement(product.getLimitedMovement());
                    return productRepository.save(oldProduct);
                })
                .map(updateProduct -> new ResponseEntity<>(updateProduct, HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public Mono<Product> deleteById(Long id) {
        return productRepository.findById(id)
                .flatMap(xRowDelete -> productRepository.delete(xRowDelete)
                        .then(Mono.just(xRowDelete)));}

    @Override
    public Mono<Boolean> existsById(Long id) {
        return null;
    }


}