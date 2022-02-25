package com.ecommerce.exceptions.user;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 3882094418918608119L;
	
	public UserException(String message) {
		super(message);
	}

}
