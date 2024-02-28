package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<Product> getAllProduct(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ) {
        SearchProductRequest request = SearchProductRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        return productService.getAll(request);
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
