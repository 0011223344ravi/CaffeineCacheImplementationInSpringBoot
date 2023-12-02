package com.example.cacheeviction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    private int id ;
    private String name;

    private Double price;

    private int quantity;
}
