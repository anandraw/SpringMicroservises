package org.anand.saga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReqesDto {

    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
}
