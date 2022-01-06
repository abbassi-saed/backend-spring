package com.ecommerce.services.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);

	UserDto getUser(String email);

	List<UserDto> getUsers(int page, int limit, String search);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String userId, UserDto userDto);

	void deletUser(String userId);

}
