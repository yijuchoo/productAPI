package com.example.productAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productAPI.entities.Product;
import com.example.productAPI.service.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductsService {

	@Autowired
	ProductRepository repo;

	@Override
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	@Override
	public Product getProductById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@Override
	public Product updateProduct(int id, Product product) {
		// Check if product exists
		return repo.findById(id).map(existingProduct -> {
			// Only update fields that are not null
			if (product.getName() != null) {
				existingProduct.setName(product.getName());
			}
			if (product.getDescription() != null) {
				existingProduct.setDescription(product.getDescription());
			}
			if (product.getPrice() != 0) {
				existingProduct.setPrice(product.getPrice());
			}
			return repo.save(existingProduct);
		}).orElse(null); // Return null if not found
	}

//	@Override
//	public Product updateProduct(int id, Product product) {
//		Product prod = null;
//
//		if (repo.findById(id) != null) {
//			prod = repo.save(product);
//		}
//		return prod;
//	}

//	@Override
//	public boolean deleteProduct(int id) {
//		boolean res = false;
//
//		if (repo.findById(id) != null) {
//			repo.deleteById(id);
//			res = true;
//		}
//		return res;
//	}

	@Override
	public boolean deleteProduct(int id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Product searchProduct(String name) {
		return repo.findByName(name);
	}

}
