package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.repository.ProductRepository;
import com.enigma.enigma_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product){
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product getById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new RuntimeException("Product Not Found");
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAll(String name, Long min, Long max, Integer stock) {
        if (name == null && min == null && max == null && stock == null) {
            return productRepository.findAll();
        } else if (name != null && min == null && max == null && stock == null) {
            return productRepository.findAllByNameLike("%" + name + "%");
        } else
            return productRepository.findAllByNameOrPriceBetweenAndStock(name,min,max,stock);

    }

    @Override
    public Product update(Product product) {
        getById(product.getId());
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(String id) {
        Product currentProduct = getById(id);
        productRepository.delete(currentProduct);
    }
}
