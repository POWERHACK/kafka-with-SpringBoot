package com.example.stockservice.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.basedomains.model.OrderEventBase;
import com.example.basedomains.model.OrdersBase;
import com.example.stockservice.model.Orders;
import com.example.stockservice.model.Stock;
import com.example.stockservice.repo.OrderRepo;
import com.example.stockservice.repo.StockRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

	@Autowired
	StockRepo repo;
	@Autowired
	StockServiceUpdate serviceUpdate;
	@Autowired
	OrderRepo orderRepo;

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(OrderEventBase event) {
		LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

	}

	public String addStock(Stock stock) {
		repo.save(stock);
		return "Stock added successfully";

	}

	public boolean isStockIsAvailable(String productName, int qty) {

		List<Stock> getAll = repo.findAll();
		for (Stock st : getAll) {
			if (st.getProductName().equalsIgnoreCase(productName) && qty <= st.getQty()) {
				
				st.setQty(st.getQty()- qty);
				addStock(st);
				return true;

			}
		}
		return false;

	}

	public String acceptOrder(String orderId) {

		List<Orders> allOrders = orderRepo.findAll();
		for (Orders ord : allOrders) {
			if (ord.getId().equals(orderId)) {

				ObjectMapper mapper = new ObjectMapper();
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

				OrdersBase orders = mapper.convertValue(ord, OrdersBase.class);

				String productName = orders.getProductNaame();
				int qty = ord.getQty();
				if (isStockIsAvailable(productName, qty)) {
					OrderEventBase orderEvent = new OrderEventBase();
					orderEvent.setStatus("ACCEPTED");
					orderEvent.setMessage("order status is Ready to deliver");
					orderEvent.setOrder(orders);

					serviceUpdate.sendMessage(orderEvent);

					return "Order Acceptd ...";

				} else {
					OrderEventBase orderEvent = new OrderEventBase();
					orderEvent.setStatus("CANCELED");
					orderEvent.setMessage("order status is Stock not Availble");
					orderEvent.setOrder(orders);

					serviceUpdate.sendMessage(orderEvent);

					return "Order Canceled ProductStock not Available ...";

				}
			}

		}

		return "Order not Available of this Order Id: "+orderId;
	}
}
