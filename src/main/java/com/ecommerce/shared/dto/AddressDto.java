package com.ecommerce.shared.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressDto  implements Serializable{


	private static final long serialVersionUID = 2791584366229618228L;
	
	private long id;
	private String addressId;
	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;
	private UserDto user;
}
