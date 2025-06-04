package com.anand;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.anand.dto.ProductRequest;
import com.anand.dto.ProductResponce;
import com.anand.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	// integration test for create product

	@Test
	void shouldCreateProduct() throws Exception {
		 ProductRequest product = getProduct();
		 String productRequest = objectMapper.writeValueAsString(product);
		 
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequest))
		    .andExpect(status().isCreated());
		Assertions.assertTrue(productRepository.findAll().size()==1);
		

	}
	
	private ProductRequest getProduct() {
		
		return ProductRequest.builder()
				.name("Iphone 13")
				.description("This is Apple device ")
				.price(BigDecimal.valueOf(1200))
				.build();
	}
	
	// integration test for get product
	@Test
	void shouldGetProduct() throws Exception {
		ProductResponce productResponce = getAllProducts();
		String pres = objectMapper.writeValueAsString(productResponce);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pres))
		    .andExpect(status().isOk());
		
		
		
	}
	
   private ProductResponce getAllProducts() {
	   return ProductResponce.builder()
				.name("Iphone 13")
				.description("This is Apple device ")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
