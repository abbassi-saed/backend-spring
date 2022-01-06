package com.ecommerce.services.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.UserEntity;
import com.ecommerce.repositories.user.UserRepository;
import com.ecommerce.services.user.UserService;
import com.ecommerce.shared.Utils;
import com.ecommerce.shared.dto.AddressDto;
import com.ecommerce.shared.dto.UserDto;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils util;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	/* this service to create a new user */
	@Override
	public UserDto createUser(UserDto user) {

		UserEntity check = userRepository.findByEmail(user.getEmail());

		if (check != null)
			throw new RuntimeException("user Already Exist");

		for (int i = 0; i < user.getAddresses().size(); i++) {

			AddressDto address = user.getAddresses().get(i);
			address.setUser(user);
			address.setAddressId(util.generateStringId(30));
			user.getAddresses().set(i, address);

		}

		user.getContact().setContactId(util.generateStringId(30));
		user.getContact().setUser(user);

		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		userEntity.setUserId(util.generateStringId(32));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity newUser = userRepository.save(userEntity);

		UserDto userDto = modelMapper.map(newUser, UserDto.class);

		return userDto;
	}

	/* this method for get User connected by email */
	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	/* this method to get all users */
	@Override
	public List<UserDto> getUsers(int page, int limit, String search) {

		if (page > 0) {
			page -= 1;
		}

		List<UserDto> usersDto = new ArrayList<>();

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<UserEntity> userPage;
		if (search.isEmpty()) {

			userPage = userRepository.findAllUsers(pageableRequest);
		} else {
			userPage = userRepository.findAllUserByCriteria(pageableRequest, search);
		}

		List<UserEntity> users = userPage.getContent();

		for (UserEntity userEntity : users) {

			ModelMapper modelMapper = new ModelMapper();
			UserDto user = modelMapper.map(userEntity, UserDto.class);

			usersDto.add(user);
		}

		return usersDto;
	}

	/* get user giving userId public */
	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	/* update user giving userId and informations */
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException(userId);

		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		UserEntity userUpdated = userRepository.save(userEntity);

		UserDto user = new UserDto();

		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	@Override
	public void deletUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);

	}

}
