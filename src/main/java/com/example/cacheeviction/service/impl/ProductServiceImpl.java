package com.example.cacheeviction.service.impl;


import com.example.cacheeviction.entity.Product;
import com.example.cacheeviction.repository.ProductRepo;
import com.example.cacheeviction.service.ProductService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        if(myCache.getIfPresent("products") !=null){
          List<Product>  al =myCache.getIfPresent("products");
            System.out.println("retrieving all products from cache");
            return al;
        }
        List<Product> allProducts = productRepo.findAll();
        myCache.put("products", allProducts);
        return allProducts;
    }

    @Override
    public Product insertProductIntoDatabase(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(int id) {
       if(myCache.getIfPresent("id") != null){
           List<Product> product = myCache.getIfPresent("id");
           System.out.println("getting product  from cache with id" +id);
           return product.get(0);
       }
        Product p =  productRepo.findById(id).get();
       myCache.put("id", Arrays.asList(p));
       return p;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productFromDB = productRepo.findById(id).get();
        productFromDB.setName(product.getName());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setQuantity(product.getQuantity());
        productRepo.save(productFromDB);
        return productFromDB;
    }

    @Override
    public Product deleteProduct(int id) {
        Product product = productRepo.findById(id).get();
        productRepo.deleteById(id);
        return product;
    }

    private Cache<String, List<Product>> myCache;

    @PostConstruct
    public void init() {
        myCache = Caffeine.newBuilder()

                .maximumSize(100) // Set maximum size
                .build();

    }


    @Override
    public void display(){
        //getAllProducts();
        System.out.println(myCache.stats());
        System.out.println(myCache.getIfPresent("id"));
       // myCache.invalidate("products");
        myCache.invalidateAll();
        System.out.println(myCache.getIfPresent("id"));
    }

  /*  public Integer get(String key) {
        return myCache.getIfPresent(key);
    }

    public void evict(String key) {
        myCache.invalidate(key);
    }

    public void evictAll() {
        myCache.invalidateAll();
    }*/
}
