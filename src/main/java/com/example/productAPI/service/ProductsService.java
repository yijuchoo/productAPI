package com.example.productAPI.service;

import java.util.List;

import com.example.productAPI.entities.Product;

public interface ProductsService {
	
	Product addProduct(Product product);
	Product getProductById(int id);
	List<Product> getAllProducts();
	Product updateProduct(int id, Product product);
	boolean deleteProduct(int id);
	Product searchProduct(String name);
}
