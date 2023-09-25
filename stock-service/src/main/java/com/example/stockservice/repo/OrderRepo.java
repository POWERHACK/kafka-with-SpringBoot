package com.example.stockservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stockservice.model.Orders;


public interface OrderRepo extends JpaRepository<Orders, String> {
	
	

}
