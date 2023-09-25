package com.example.stockservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.stockservice.kafka.StockService;
import com.example.stockservice.model.Stock;

@RestController
public class StockController {
	
	
	
	
	@Autowired
	StockService service;
	
	
	@PostMapping("/addproduct")
	public String addProduct(@RequestBody Stock stock) {
		
		String str =service.addStock(stock);
		
		return str;
	}
	
	@GetMapping("/acceptOrder/{orderId}")
	public String acceptOrderById(@PathVariable String orderId) {
		
		String status = service.acceptOrder(orderId);
		
		return status;
		
	}
	

}
