package com.example.cacheeviction.repository;


import com.example.cacheeviction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
