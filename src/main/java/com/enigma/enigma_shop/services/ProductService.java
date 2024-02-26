package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.entity.Product;

import java.util.List;

public interface ProductService {


    Product create(Product product);
    Product getById(String id);
    List<Product> getAll(String name, Long min, Long max, Integer stock);
    Product update(Product product);
    void delete(String id);

}
