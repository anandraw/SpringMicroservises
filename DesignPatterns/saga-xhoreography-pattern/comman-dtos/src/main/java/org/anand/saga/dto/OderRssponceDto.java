package org.anand.saga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anand.saga.event.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OderRssponceDto {
    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
