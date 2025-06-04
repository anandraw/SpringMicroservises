package org.anand.service;

import org.anand.dto.OrderRequest;
import org.anand.dto.OrderResponse;
import org.anand.dto.OrderStatus;
import org.anand.entity.OrderEvent;
import org.anand.publisher.OrderEventKafkaPublisher;
import org.anand.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderEventKafkaPublisher publisher;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();
        orderRequest.setOrderId(orderId);
        OrderEvent orderEvent=new OrderEvent(orderId, OrderStatus.CREATED,"Order created successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CREATED);
    }
    // Handle order confirmation
    public OrderResponse confirmOrder(String orderId) {
        OrderEvent orderEvent=new OrderEvent(orderId,OrderStatus.CONFIRMED,"Order confirmed successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
    }

    private void saveAndPublishEvents(OrderEvent orderEvent){
        orderRepo.save(orderEvent);
        publisher.sendOrderEvent(orderEvent);
    }
}
