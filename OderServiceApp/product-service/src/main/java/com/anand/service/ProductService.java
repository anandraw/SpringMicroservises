package com.anand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.dto.ProductRequest;
import com.anand.dto.ProductResponce;
import com.anand.model.Product;
import com.anand.repo.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	 public void createProduct(ProductRequest productRequest) {
		   Product product = Product.builder()
	                .name(productRequest.getName())
	                .description(productRequest.getDescription())
	                .price(productRequest.getPrice())
	                .build();

	        productRepository.save(product);
	        log.info("Product {} is saved", product.getId());
	 }
	 
	 public List<ProductResponce> getAllProducts() {
	        List<Product> products = productRepository.findAll();

	        return products.stream().map(this::mapToProductResponse).toList();
	    }
	 
	 private ProductResponce mapToProductResponse(Product product) {
	        return ProductResponce.builder()
	                .id(product.getId())
	                .name(product.getName())
	                .description(product.getDescription())
	                .price(product.getPrice())
	                .build();
	    }

	
	

}
