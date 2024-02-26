package com.enigma.enigma_shop;

import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class EnigmaShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(EnigmaShopApplication.class, args);
	}


/*	private ProductService productService;

	public EnigmaShopApplication(ProductService productService) {
		this.productService = productService;
	}*/

/*
	@GetMapping(path = "/hello-world", produces = MediaType.APPLICATION_JSON_VALUE)
	public String helloWorld(){
		return "<h1>helloWorld</h1>";
	}

	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProduct(){
		return Map.of(
				"id",1,
				"name", "Apel",
				"price", 3000
		);
	}

	@GetMapping(path = "/menus")
	public List<Map<String,Object>> getMenusFilter(
			@RequestParam(name = "nama", required = false) String name,
			@RequestParam(name = "maxPrice",required = false) Integer maxPrice
			) {
		Map<String, Object> menu = Map.of(
				"id", 1,
				"name", "Pentol",
				"price", 3000
		);
		List<Map<String,Object>> menus = new ArrayList<>();
		menus.add(menu);
		return menus;
	}

	@GetMapping(path = "/menus/{id}")
	public String getMenuById(@PathVariable(name = "id") String menuId) {
		return "Product " + menuId;
	}

*/

	/*	@PostMapping(
			path = "/products",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public Product createNewProduct(@RequestBody Product product) {
		return product;
	}*/

	/*

	@GetMapping(
			path = "/products",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public List<Product> getProducts(){
		return productService.findAllProducts();
	}

	@PostMapping(
			path = "/products/save",
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public void postProducts(Product product){
		productService.saveProduct(product);
	}*/

}
