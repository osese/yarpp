package com.osese.Yarpp.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired(required=true)
	CategoryRepo categoryRepo;
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
	
}
