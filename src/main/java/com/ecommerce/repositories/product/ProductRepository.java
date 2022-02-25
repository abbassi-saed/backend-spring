package com.ecommerce.repositories.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.entities.ProductEntity;

public interface ProductRepository  extends PagingAndSortingRepository<ProductEntity, Long>{

	
	ProductEntity findByProductId(String productId);
}
