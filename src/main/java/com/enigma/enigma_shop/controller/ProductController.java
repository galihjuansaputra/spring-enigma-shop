package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getById(id);
    }

    @GetMapping
    public List<Product> getAllProduct(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "min", required = false) Long min,
            @RequestParam(name = "max", required = false) Long max,
            @RequestParam(name = "stock", required = false) Integer stock
            ) {

        return productService.getAll(name, min, max, stock);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        productService.delete(id);
        return "OK";
    }

}
