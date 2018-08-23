package com.osese.Yarpp.Category;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osese.Yarpp.Product.Product;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@GetMapping
	public Iterable<Category> getAll() {	
		return categoryRepo.findAll();
	}
	
	@PostMapping
	public Category addCategory(@RequestBody Category cat) {
		return categoryRepo.save(cat);
	}
	 	
	@GetMapping("/{id}")
	public Category getOne(@PathVariable Integer id) {	
		return categoryRepo.findById(id).get();
	}
	
	@GetMapping("/{id}/products")
	public Collection<Product> getProductsOfCat(@PathVariable Integer id){
		return categoryRepo.findById(id).get().getProducts();
	}
	/*
	 * En babası kategorinin adını değiştirir.
	 **/
	@PatchMapping("/{id}")
	public void editCategory(@PathVariable Integer id, @RequestParam("name") String name) {
		categoryRepo.setCategoryNameForId(name, id);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteCat(@PathVariable Integer id) {
		if(categoryRepo.findById(id).get().getProducts().isEmpty()) {
			categoryRepo.deleteById(id);
			return "okey";
		}
		return "fail";
	}
	
	
}
