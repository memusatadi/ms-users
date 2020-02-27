package com.task.ms.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.ms.users.dto.UserDto;
import com.task.ms.users.dto.UserResponseDto;
import com.task.ms.users.service.UsersService;

/**
 * Controller : Endpoint de creación de usuarios. 
 * @param: UserDto
 * @author mmusatad
 *
 */

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseDto addUser(@RequestBody @Valid UserDto user) {
		return usersService.recordUser(user);

	}
	
	@GetMapping(value = "/users/{email:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseDto getUser(@PathVariable("email") String email) {
		return usersService.getUser(email);

	}

}
