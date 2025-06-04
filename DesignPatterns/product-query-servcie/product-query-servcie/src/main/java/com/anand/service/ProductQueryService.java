package com.anand.service;

import com.anand.entity.Product;
import com.anand.event.ProductEvent;
import com.anand.repo.ProductRepo;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepo repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }


    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        Product product = productEvent.getProduct();

        try {
            switch (productEvent.getEventType()) {
                case "CreateProduct":
                    product.setVersion(0);
                    repository.saveAndFlush(product);  // Immediately flush to DB
                    break;

                case "UpdateProduct":
                    Product existingProduct = repository.findById(product.getId())
                            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + product.getId()));

                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setDescription(product.getDescription());

                    repository.saveAndFlush(existingProduct);  // Immediately flush to ensure versioning
                    break;

                default:
                    throw new IllegalArgumentException("Unknown event type: " + productEvent.getEventType());
            }
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Concurrent modification detected for Product ID: " + product.getId(), e);
        }
    }
}
