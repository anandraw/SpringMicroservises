package org.anand.saga.service;

import org.anand.saga.dto.OrderReqesDto;
import org.anand.saga.entity.PurchesOrder;
import org.anand.saga.event.OrderStatus;
import org.anand.saga.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchesOrder createOrder(OrderReqesDto orderReqesDto){
        PurchesOrder order = orderRepo.save(dtoToEntity(orderReqesDto));
        orderReqesDto.setOrderId(order.getId());

        //produce kafka event
        orderStatusPublisher.publishOrderEvent(orderReqesDto, OrderStatus.ORDER_CREATED);
        return order;
    }

    public List<PurchesOrder> getAllOrders(){
        return orderRepo.findAll();
    }

    private PurchesOrder dtoToEntity(OrderReqesDto orderReqesDto){
        return modelMapper.map(orderReqesDto, PurchesOrder.class);
    }
}
