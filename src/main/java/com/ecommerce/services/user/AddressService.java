package com.ecommerce.services.user;

import java.util.List;

import com.ecommerce.shared.dto.AddressDto;

public interface AddressService {

	List<AddressDto> getAllAddresses(String email);

	AddressDto createAddress(AddressDto addressDto, String email);

	AddressDto getAddress(String addressId);

	void deleteAddress(String addressId);

}
