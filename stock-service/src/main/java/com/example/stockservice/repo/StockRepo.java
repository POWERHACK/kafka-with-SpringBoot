package com.example.stockservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stockservice.model.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer>{

}
