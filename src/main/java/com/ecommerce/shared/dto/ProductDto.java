package com.ecommerce.shared.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2009416700204955209L;

	private long id;
	private String productId;	
	private String title;
	private double price;
	private int quantity;
	private String description;
	private String image;
	private String category;
//	private UserDto user;

}
