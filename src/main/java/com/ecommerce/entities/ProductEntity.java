package com.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;

@Entity(name="products")
@Getter @Setter
public class ProductEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length = 30, nullable = false)
	private String productId;
	
	@Column(length = 20, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private double price;
	
	@Column(length = 120, nullable = false)
	private String description ;
	
	@Column(length = 120, nullable = false)
	private String image;
	
	@Column(length = 20, nullable = false)
	private String category;
	
	@Column(nullable = false)
	private double quantity;
		
//	@ManyToOne
//	@JoinColumn(name = "users_id")
//	private UserEntity user;
	
	
}
