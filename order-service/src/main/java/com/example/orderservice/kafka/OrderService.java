package com.example.orderservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.basedomains.model.OrderEventBase;
import com.example.basedomains.model.OrdersBase;
import com.example.orderservice.model.Orders;
import com.example.orderservice.repo.OrderRepo;

@Service
public class OrderService {
	@Autowired
	OrderRepo repo;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private NewTopic topic;

    private KafkaTemplate<String, OrderEventBase> kafkaTemplate;

    public OrderService(NewTopic topic, KafkaTemplate<String, OrderEventBase> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEventBase event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<OrderEventBase> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
    
    
    public String saveOrder(Orders orders) { 	
    	repo.save(orders);
    	return "Order placed successfully ...";   
    	}
    
}
