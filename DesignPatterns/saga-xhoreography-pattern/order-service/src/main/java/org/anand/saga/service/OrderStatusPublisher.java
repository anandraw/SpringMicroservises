package org.anand.saga.service;

import org.anand.saga.dto.OrderReqesDto;
import org.anand.saga.event.OrderEvent;
import org.anand.saga.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderReqesDto orderReqesDto, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderReqesDto,orderStatus);
        orderSinks.tryEmitNext(orderEvent);

    }
}
