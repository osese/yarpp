package com.osese.Yarpp.OrderLine;

import org.springframework.data.rest.core.config.Projection;

import com.osese.Yarpp.Product.Product;

@Projection(name="productbundleExcerp", types=OrderLine.class)
public interface OrderLineExcerpt {
	String getProductName();
	String getProductPrice();
	Double getTotal();
	Integer getCount();
}
