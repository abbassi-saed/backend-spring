package com.ecommerce.responses.product;


//import com.ecommerce.shared.dto.UserDto;

import lombok.Data;

@Data
public class ProductResponse {

		
	private String productId;
	private String title;
	private double price;
	private int quantity;
	private String description ;
	private String image;
	private String category;
//	private UserDto user;
	
}
