package com.example.orderservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.basedomains.model.OrderEventBase;
import com.example.basedomains.model.OrdersBase;
import com.example.orderservice.kafka.OrderService;
import com.example.orderservice.model.Orders;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.orderservice.model.OrderEvent;


@RestController
//@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public String placeOrder(@RequestBody OrdersBase ordersbase){
    	
    	ordersbase.setId(UUID.randomUUID().toString());
        OrderEventBase orderEvent = new OrderEventBase();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(ordersbase);

		orderService.sendMessage(orderEvent);

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		Orders orders = mapper.convertValue(ordersbase, Orders.class);
		String ord = orderService.saveOrder(orders);
		return ord+" Your OrderId is : "+ordersbase.getId();
        
    }

    }