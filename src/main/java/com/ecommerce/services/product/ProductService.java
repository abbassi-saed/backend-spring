package com.ecommerce.services.product;


import java.util.List;

import com.ecommerce.shared.dto.ProductDto;

public interface ProductService {

	
	ProductDto createProduct(ProductDto product /* String email*/);
	
	List<ProductDto> getProducts(int page,int limit);
	
	void deletProduct(String prodId);
}
