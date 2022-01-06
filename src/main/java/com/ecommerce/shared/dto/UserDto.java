package com.ecommerce.shared.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

// to implement all getters and setters and Constructors
@Data
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8538960946299088350L;

	private long  id;
	private String userId;
	private String firstName;
	private String lastName;
//	private String nameUser;
	private String email;
	private Boolean admin;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private List<AddressDto> addresses;
	private ContactDto contact;

}
