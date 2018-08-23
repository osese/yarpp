package com.osese.Yarpp.Category;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

import com.osese.Yarpp.Product.Product;

@Projection(name="categoryExcerpt", types=Category.class)
public interface CategoryExcerpt {
	
	String getName();
	Collection<Product> getProducts();
}
