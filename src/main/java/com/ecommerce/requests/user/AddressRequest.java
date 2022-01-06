package com.ecommerce.requests.user;

import lombok.Data;

@Data
public class AddressRequest {

	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;
}
