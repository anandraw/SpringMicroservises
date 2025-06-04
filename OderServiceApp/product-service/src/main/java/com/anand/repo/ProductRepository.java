package com.anand.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anand.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
