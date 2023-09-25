package com.example.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderservice.model.Orders;

  
public interface OrderRepo extends JpaRepository<Orders, String>{
	
}