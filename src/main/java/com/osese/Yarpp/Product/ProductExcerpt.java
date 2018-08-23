package com.osese.Yarpp.Product;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

import com.osese.Yarpp.Category.Category;

@Projection(name="productExcerpt", types=Product.class)
public interface ProductExcerpt {
	
	String getName();
	Collection<Category> getCategories();
}
