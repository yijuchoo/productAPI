package com.example.productAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productAPI.entities.Product;
import com.example.productAPI.service.ProductsService;

@RestController
@RequestMapping("/api/customer/products")
public class ProductCustomerController {
	
	@Autowired
	ProductsService service;
	
	@GetMapping("/get/{id}")
	public Product getProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	
	@GetMapping("getAll")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	@GetMapping("/search")
	public Product searchProduct(@RequestParam String name) {
		return service.searchProduct(name);
	}

}
