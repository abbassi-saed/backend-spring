package com.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exceptions.product.ProductException;
import com.ecommerce.requests.product.ProductRequest;
import com.ecommerce.responses.product.ProductErrorMessages;
import com.ecommerce.responses.product.ProductResponse;
import com.ecommerce.services.product.ProductService;
import com.ecommerce.shared.dto.ProductDto;

@RestController()
@RequestMapping("products")
public class ProductController {

	@Autowired
	ProductService productService;

	// create product
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest /*,Principal principal*/)
			throws Exception {

		if (productRequest.getTitle().isBlank() || productRequest.getDescription().isBlank()
				|| productRequest.getImage().isBlank() || productRequest.getPrice() == 0)
			throw new ProductException(ProductErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();

		ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);

		ProductDto createProduct = productService.createProduct(productDto /*,principal.getName()*/);

		ProductResponse productResponse = modelMapper.map(createProduct, ProductResponse.class);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit) {

		List<ProductResponse> productsResponse = new ArrayList<>();

		List<ProductDto> products = productService.getProducts(page, limit);

		for (ProductDto productDto : products) {
			ModelMapper modelMapper = new ModelMapper();

			ProductResponse product = modelMapper.map(productDto, ProductResponse.class);

			productsResponse.add(product);
		}
		return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
	}
	
	
	/* delete product giving Id public => Http.Status.NO_CONTENT */
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Object> deletUser(@PathVariable String userId) {

		productService.deletProduct(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	


	
}
