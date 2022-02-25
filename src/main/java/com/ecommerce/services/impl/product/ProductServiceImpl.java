package com.ecommerce.services.impl.product;


import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ecommerce.entities.ProductEntity;
//import com.ecommerce.entities.UserEntity;
import com.ecommerce.repositories.product.ProductRepository;
import com.ecommerce.repositories.user.UserRepository;
import com.ecommerce.services.product.ProductService;
import com.ecommerce.shared.Utils;
import com.ecommerce.shared.dto.ProductDto;
//import com.ecommerce.shared.dto.UserDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils util;

	@Override
	public ProductDto createProduct(ProductDto product /*String email*/) {
 
		// TODO : Get Current User
//		UserEntity currentUser = userRepository.findByEmail(email);

		ModelMapper modelMapper = new ModelMapper();
//		UserDto userDto = modelMapper.map(currentUser, UserDto.class);

		product.setProductId(util.generateStringId(15));
		product.setImage("nofound.png");
//		product.setUser(userDto);
//		System.out.println(product.toString());

		ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);

		productEntity.setProductId(util.generateStringId(15));

		ProductEntity newProduct = productRepository.save(productEntity);

		ProductDto productDto = modelMapper.map(newProduct, ProductDto.class);

		return productDto;

	}

//	@Override
//	public List<ProductDto> getProducts(int page, int limit) {
//
//		List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
//
//		Type listType = new TypeToken<List<ProductEntity>>() {
//		}.getType();
//		
//		List<ProductDto> productsDto = new ModelMapper().map(products, listType);
//
//		return productsDto;
//	}
	
	@Override
	public List<ProductDto> getProducts(int page, int limit) {

		List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
		List<ProductDto> productDtos = new  ArrayList<>();
		
		for(ProductEntity productEntity : products ) {
			
			ModelMapper modelMapper = new ModelMapper();
			ProductDto p1 = modelMapper.map(productEntity, ProductDto.class);
			productDtos.add(p1);
		}
//		Type listType = new TypeToken<List<ProductEntity>>() {
//		}.getType();
		
//		List<ProductDto> productsDto = (List<ProductDto>) new ModelMapper().map(products, ProductDto.class);

		return productDtos;
	}

	@Override
	public void deletProduct(String prodId) {


		ProductEntity userEntity = productRepository.findByProductId(prodId);
		if (userEntity == null)
			throw new UsernameNotFoundException(prodId);
		productRepository.delete(userEntity);

		
	}

}
