package org.anand.saga.event;

import lombok.NoArgsConstructor;
import org.anand.saga.dto.OrderReqesDto;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class OrderEvent implements Event{

    private UUID eventId=UUID.randomUUID();
    private Date eventDate= new Date();
    private OrderReqesDto orderReqesDto;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public OrderEvent(OrderReqesDto orderReqesDto, OrderStatus orderStatus) {
        this.orderReqesDto = orderReqesDto;
        this.orderStatus = orderStatus;
    }
}
