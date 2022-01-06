package com.ecommerce.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
	
	private long id;
	private String contactId;
	private String mobile;
	private String skype;
	private UserDto user;
}
