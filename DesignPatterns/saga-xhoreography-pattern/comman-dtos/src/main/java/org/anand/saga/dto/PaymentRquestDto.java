package org.anand.saga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRquestDto {

    private Integer userId;
    private Integer amount;
    private Integer orderId;
}
