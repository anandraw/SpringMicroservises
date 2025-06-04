package com.anand.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.anand.dto.InventoryResponce;
import com.anand.dto.OrderItemListDto;
import com.anand.dto.OrderRequest;
import com.anand.event.OrderPlaceEvent;
import com.anand.model.Order;
import com.anand.model.OrderLineItems;
import com.anand.repo.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	@Autowired
	private KafkaTemplate<String,OrderPlaceEvent> kafkaTemplat;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	public String placeOrder(OrderRequest orderRequest) {
	    Order order = new Order();
	    order.setOrderNumber(UUID.randomUUID().toString());
	    
	    // Existing logic for mapping and checking stock
	    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream()
	            .map(this::mapToDto).toList();
	    order.setOrderLineItemsList(orderLineItems);

	    List<String> skuCodes = orderLineItems.stream().map(OrderLineItems::getSkuCode).toList();

	    InventoryResponce[] inventoryResponseArray = webClientBuilder.build().get()
	            .uri("http://inventory-service/api/inventory",
	                    uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
	            .retrieve().bodyToMono(InventoryResponce[].class).block();

	    boolean allInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponce::isInStock);

	    if (allInStock) {
	        orderRepo.save(order);
	        kafkaTemplat.send("notificationTopic", new OrderPlaceEvent(order.getOrderNumber()));
	        log.info("Order placed successfully: {}", order.getOrderNumber());
	        return "Order placed successfully with Order Number: " + order.getOrderNumber();
	    } else {
	        log.warn("Some products are not in stock: {}", skuCodes);
	        throw new RuntimeException("One or more products are not in stock.");
	    }
	}

	private OrderLineItems mapToDto(OrderItemListDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
