package com.osese.Yarpp.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osese.Yarpp.Category.Category;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping
	public List<Product> getAll() {
		return productService.findAll();
	}
	

	@GetMapping("/{id}")
	public Product getOne(@PathVariable Integer id) {
		return productService.findById(id).get();
	}
	
	@PostMapping
	public Product addProduct(@RequestBody Product product) {	
	
		Product p = productService.save(product);
		p.getCategories();
		return p;
	}
	
	@PatchMapping("/{id}")
	public Product editProduct(@PathVariable Integer id , @RequestBody Product product) {
		productService.updateProduct(id, product.getCode(), product.getImage(), product.getName(), product.getPrice());
		return productService.findById(id).get();
	}
	
	
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteById(id);
	}
	
}
