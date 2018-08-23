package com.osese.Yarpp.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osese.Yarpp.Category.Category;
import com.osese.Yarpp.Category.CategoryRepo;
@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	public Product save(Product product) {
		if(product.getCategories() == null) {
			// INFO: this place perfect for default category ! 
			return productRepo.save(product);
		}
		// Gelen ürünün kategorisi kayıtlı olmalıdır. 	
		return productRepo.save(product);
	}

	public List<Product> findAll() {
		return productRepo.findAll();
	}

	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);
	}

	public void updateProduct(Integer id, String code, byte[] image, String name, Double price) {
		productRepo.updateProduct(id, code, image, name, price);
	}

	public void deleteById(Integer id) {
		productRepo.deleteById(id);
	}

	
}
