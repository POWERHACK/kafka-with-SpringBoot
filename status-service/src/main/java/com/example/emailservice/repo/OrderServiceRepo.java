package com.example.emailservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emailservice.model.OrderStatus;

public interface OrderServiceRepo extends JpaRepository<OrderStatus, String>{}