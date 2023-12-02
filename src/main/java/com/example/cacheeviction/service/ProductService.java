package com.example.cacheeviction.service;



import com.example.cacheeviction.entity.Product;

import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();

    Product insertProductIntoDatabase(Product product);

    Product getProductById(int id );

    Product updateProduct(int id, Product product);

    Product deleteProduct(int id);

    void display();


}
