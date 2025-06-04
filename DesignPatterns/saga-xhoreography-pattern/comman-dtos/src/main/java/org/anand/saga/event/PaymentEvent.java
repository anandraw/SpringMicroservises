package org.anand.saga.event;

import lombok.NoArgsConstructor;
import org.anand.saga.dto.PaymentRquestDto;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class PaymentEvent implements Event{

    private UUID eventId=UUID.randomUUID();
    private Date eventDate= new Date();
    private PaymentRquestDto paymentRquestDto;
    private PaymentStatus paymentStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public PaymentEvent(PaymentRquestDto paymentRquestDto, PaymentStatus paymentStatus) {
        this.paymentRquestDto = paymentRquestDto;
        this.paymentStatus = paymentStatus;
    }

}
