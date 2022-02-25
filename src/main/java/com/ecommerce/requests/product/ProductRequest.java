package com.ecommerce.requests.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductRequest {

	@NotBlank(message = "ce champs ne doit pas être null")
	@Size(min = 3, max = 50, message = " ce champ doit etre entre 3 et 50 caractaire")
	private String title;

	@PositiveOrZero
	private double price;

	@PositiveOrZero
	private int quantity;
	
	@NotBlank(message = "ce champs ne doit pas être null")
	@Size(min = 3, max = 120, message = " ce champ doit etre entre 3 et 120 caractaire")
	private String description;

//	@NotBlank(message = "ce champs ne doit pas être null")
//	@Size(min = 3, max = 50, message = " ce champ doit etre entre 3 et 50 caractaire")
	private String image ="nofound.png";

	@NotBlank(message = "ce champs ne doit pas être null")
	@Size(min = 3, max = 50, message = " ce champ doit etre entre 3 et 50 caractaire")
	private String category;
}
