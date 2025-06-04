package com.anand.service;

import com.anand.entity.Product;
import com.anand.event.ProductEvent;
import com.anand.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProductCommandService {

    @Autowired
    private ProductRepo repository;

    @Autowired
    private  KafkaTemplate<String,Object> kafkaTemplate;


    public Product createProduct(ProductEvent productEvent) {
        if (productEvent == null || productEvent.getProduct() == null) {
            throw new IllegalArgumentException("Product event or product cannot be null");
        }
        Product productDO = repository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }


    public Product updateProduct(long id, ProductEvent productEvent) {
        if (productEvent == null || productEvent.getProduct() == null) {
            throw new IllegalArgumentException("Product event or product cannot be null");
        }

        Product existingProduct = repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with ID " + id + " not found"));

        Product newProduct = productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setDescription(newProduct.getDescription());

        Product productDO = repository.save(existingProduct);
        ProductEvent event = new ProductEvent("UpdateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);

        return productDO;
    }

}

