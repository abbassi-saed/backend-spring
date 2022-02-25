package com.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exceptions.user.UserException;
import com.ecommerce.requests.user.UserRequest;
import com.ecommerce.responses.user.UserErrorMessages;
import com.ecommerce.responses.user.UserResponse;
import com.ecommerce.services.user.UserService;
import com.ecommerce.shared.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

// to declare this class its a rest controller
@RestController

// all route for this class must be prefixed by the name user
@RequestMapping("users")

@Getter
@Setter
public class UserController {

	@Autowired
	private UserService userService;

	/* to create a new user */

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) throws Exception {

		if (userRequest.getFirstName().isEmpty() || userRequest.getLastName().isEmpty()
				|| userRequest.getEmail().isEmpty() || userRequest.getPassword().isEmpty())
			throw new UserException(UserErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		// UserDto userDto = new UserDto();
		// BeanUtils.copyProperties(userRequest, userDto);

		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
		
		

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	/* this method to get all users */

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit,
			@RequestParam(value = "search", defaultValue = "") String search) {

		List<UserResponse> usersResponse = new ArrayList<>();

		List<UserDto> users = userService.getUsers(page, limit, search);

		for (UserDto userDto : users) {

			ModelMapper modelMapper = new ModelMapper();

			UserResponse user = modelMapper.map(userDto, UserResponse.class);

			usersResponse.add(user);
		}
		return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
	}

	/* get user by Id => HttpStatus.Ok : 200 OK. */
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {

		UserDto userDto = userService.getUserByUserId(userId);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(userDto, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	/* update user giving Id Public => HttpStatus.ACCEPTED : 202 Accepted */
	@PutMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable String userId,
			@Valid @RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userRequest, userDto);

		UserDto createUser = userService.updateUser(userId, userDto);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(createUser, userResponse);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);

	}

	/* delete user giving Id public => Http.Status.NO_CONTENT */
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Object> deletUser(@PathVariable String userId) {

		userService.deletUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
