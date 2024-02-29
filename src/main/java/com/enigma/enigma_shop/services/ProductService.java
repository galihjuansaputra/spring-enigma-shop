package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.request.NewProductRequest;
import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product create(NewProductRequest product);
    Product getById(String id);
    Page<Product> getAll(SearchProductRequest request);
    Product update(Product product);
    void delete(String id);

}
