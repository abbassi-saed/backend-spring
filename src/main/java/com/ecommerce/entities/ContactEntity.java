package com.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "contacts")
public class ContactEntity implements Serializable {

	private static final long serialVersionUID = -1255779869505476859L;

	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Column(length = 30)
	private String contactId;

	@NotBlank
	@Column(length = 30)
	private String mobile;

	@Column(length = 30)
	private String skype;

	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;

}
