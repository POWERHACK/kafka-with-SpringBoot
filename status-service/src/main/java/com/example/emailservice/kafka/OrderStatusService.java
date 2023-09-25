package com.example.emailservice.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.basedomains.model.OrderEventBase;
import com.example.emailservice.model.OrderStatus;
import com.example.emailservice.repo.OrderServiceRepo;


@Service
public class OrderStatusService {
	
	@Autowired
	OrderServiceRepo orderServiceRepo;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusService.class);

	@KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEventBase event){
        LOGGER.info(String.format("Order Status recived from stock service => %s", event.toString()));
    
        
		OrderStatus orderStaus = new OrderStatus();
		orderStaus.setOrderId(event.getOrder().getId());
		orderStaus.setConsumerName(event.getOrder().getCustomerName());
		orderStaus.setProductName(event.getOrder().getProductNaame());
		orderStaus.setStatus(event.getStatus());
		orderStaus.setMessage(event.getMessage());
		saveOrderStatus(orderStaus);
    
	}

	public void saveOrderStatus(OrderStatus orderStatus) {
		
		orderServiceRepo.save(orderStatus);		
	}
	
public void updateOrderStatus(OrderStatus orderStatus) {
		
		orderServiceRepo.save(orderStatus);		
	}
	
	public OrderStatus getOrderStatusById(String orderId) {
		List<OrderStatus> orderList = orderServiceRepo.findAll();	
		for(OrderStatus sts :orderList)
		if(sts.getOrderId().equals(orderId)) {
 		OrderStatus orderStatus = sts;
		return orderStatus;
		}
		
		return null;
	}
	
}
