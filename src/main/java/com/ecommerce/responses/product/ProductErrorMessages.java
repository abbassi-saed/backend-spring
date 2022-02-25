package com.ecommerce.responses.product;

public enum ProductErrorMessages {

	MISSING_REQUIRED_FIELD("Missing required field."), 
//	RECORD_ALREADY_EXISTS("Record already exists."),
	INTERNAL_SERVER_ERROR("Internal Bright coding server error."),
	NO_RECORD_FOUND("Record with provided id is not found.");

	private String errorMessage;

	private ProductErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
