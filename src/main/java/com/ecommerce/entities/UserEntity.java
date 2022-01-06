package com.ecommerce.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

// to generate getters and setters
@Getter
@Setter

// to declare its a entity and give it a name
@Entity(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -2677854956073041994L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 120)
	private String userId;

	@Column(nullable = false, length = 120)
	private String firstName;

	@Column(nullable = false, length = 120)
	private String lastName;

	@Column(nullable = false, unique = true, length = 120)
	private String email;
	
	@Column(nullable = true)
	private Boolean admin = false;

	@Column(nullable = false, length = 120)
	private String encryptedPassword;

	@Column(nullable = true, length = 120)
	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private ContactEntity contact;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
	private Set<GroupEntity> groups = new HashSet<>();
	

}
