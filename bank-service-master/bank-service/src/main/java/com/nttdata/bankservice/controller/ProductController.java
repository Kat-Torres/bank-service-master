package com.nttdata.bankservice.controller;


import com.nttdata.bankservice.model.Product;
import com.nttdata.bankservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Mono<Product> register(@RequestBody Product product){return productService.save(product);}

    @GetMapping
    public Flux<Product>getAll(){return productService.getAll();}

    @GetMapping(path = {"{id}"},produces = {"application/json"})
    public Mono<Product> findById(@PathVariable("id")Long id){return productService.findById(id);}

    @PutMapping(path = {"{id}"}, produces = {"application/json"})
    public Mono<ResponseEntity<Product>>update(@PathVariable("id")Long id,@RequestBody Product product){
        return productService.update(id, product)
                .map(r-> ResponseEntity.noContent().<Product>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());}


    @DeleteMapping(path={"{id}"})
    public Mono<ResponseEntity<Void>>deleteById(@PathVariable("id") Long id){
        return productService.deleteById(id)
                .map(r->ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());}
}
