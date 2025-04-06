package com.example.productAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.productAPI.entities.Product;
import com.example.productAPI.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductsService service;

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product res = null;
		try {
			res = service.addProduct(product);
			return new ResponseEntity<Product>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Product>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product res = null;
		try {
			res = service.getProductById(id);
			return new ResponseEntity<Product>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(res, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> products = service.getAllProducts();

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 if no products
			}

			return new ResponseEntity<>(products, HttpStatus.OK); // 200 if products found
		} catch (Exception e) {
			e.printStackTrace(); // Log the error properly in real applications
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 on server error
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {

		try {
			Product updatedProduct = service.updateProduct(id, product);

			if (updatedProduct != null) {
				// If the product is found and updated, return it with status OK (200)
				return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
			} else {
				// If no product is found with the given id, return NOT_FOUND (404)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// In case of any exception, return an internal server error
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PutMapping("/update/{id}")
//	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
//		Product updatedProduct = service.updateProduct(id, product);
//		if (updatedProduct == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//	}

//	@DeleteMapping("/delete/{id}")
//	public boolean deleteProduct(@PathVariable int id) {
//
//		return service.deleteProduct(id);
//	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
		try {
			boolean isDeleted = service.deleteProduct(id);
			if (isDeleted) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search")
	public Product searchProduct(@RequestParam String name) {

		return service.searchProduct(name);
	}

}
