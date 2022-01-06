package com.ecommerce.requests.user;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank(message = "ce champs ne doit pas être null")
	@Size(min = 3, max = 50, message = " ce champ doit etre entre 3 et 50 caractaire")
	private String firstName;

	@NotBlank(message = "ce champs ne doit pas être null")
	@Size(min = 3, max = 50, message = " ce champ doit etre entre 3 et 50 caractaire")
	private String lastName;

	@NotBlank(message = "ce champs ne doit pas être null")
	@Size(min = 3, max = 50, message = " ce champ doit etre entre 3 et 50 caractaire")
	private String nameUser;

	@NotBlank(message = "ce champs ne doit pas être null")
	@Email
	private String email;

	@NotBlank(message = "ce champs ne doit pas etre null ")
	@Size(min = 4, message = " ce champ doit etre avoir au mois 4 caractaires")
	@Size(max = 16, message = " ce champ doit etre avoir au maximum 4 caractaires")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "ce  mot de pass doit avoir correct format")
	private String password;

	private Boolean admin;

	private List<AddressRequest> addresses;

	private ContactRequest contact;
}
