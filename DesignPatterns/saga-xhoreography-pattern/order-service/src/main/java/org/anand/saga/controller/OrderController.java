package org.anand.saga.controller;

import org.anand.saga.dto.OrderReqesDto;
import org.anand.saga.entity.PurchesOrder;
import org.anand.saga.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public PurchesOrder createOrder(@RequestBody OrderReqesDto orderReqesDto){
        return orderService.createOrder(orderReqesDto);
    }

}
