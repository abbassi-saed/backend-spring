package com.ecommerce.repositories.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.AddressEntity;
import com.ecommerce.entities.UserEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

	List<AddressEntity> findByUser(UserEntity currentUser);

	AddressEntity findByAddressId(String addressId);

}
