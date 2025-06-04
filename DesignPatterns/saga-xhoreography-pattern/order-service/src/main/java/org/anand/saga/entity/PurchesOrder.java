package org.anand.saga.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anand.saga.event.OrderStatus;
import org.anand.saga.event.PaymentStatus;

@Entity
@Table(name = "PURCHASE_ORDER_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchesOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
