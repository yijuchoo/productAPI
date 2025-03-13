package com.example.productAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productAPI.entities.Product;
import com.example.productAPI.service.ProductsService;

@RestController
@RequestMapping("/api/admin/products")
public class ProductAdminController {
	
	@Autowired
	ProductsService service;
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product res = null;
		try {
			res = service.addProduct(product);
			return new ResponseEntity<Product>(res,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Product>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
		return service.updateProduct(id, product);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

}
