package com.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "addresses")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = -5049779996946256246L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String addressId;

	@Column(length = 20, nullable = false)
	private String city;

	@Column(length = 20, nullable = false)
	private String country;

	@Column(length = 50, nullable = false)
	private String street;

	@Column(length = 7, nullable = false)
	private String postal;

	@Column(length = 20, nullable = false)
	private String type;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;

}
