package com.anand.controller;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.anand.dto.OrderRequest;
import com.anand.services.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
	

	@Autowired
	private OrderService orderService;

	// here implement the circuit breaker pattern
	
//	@PostMapping
//	@ResponseStatus(value = HttpStatus.CREATED)
//	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethode")
//	@TimeLimiter(name = "inventory")
//	@Retry(name = "inventory")
//	 public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
//        log.info("Placing Order");
//        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
//    }
//
//	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
//		log.info("Cannot Place Order Executing Fallback logic");
//		return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
//	}
	
	
	// without pattern
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
//	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethode")
//	@TimeLimiter(name = "inventory")
//	@Retry(name = "inventory")
	 public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Placing Order");
        return orderService.placeOrder(orderRequest);
    }
}
