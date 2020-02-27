package com.task.ms.users.service;

import com.task.ms.users.dto.UserDto;
import com.task.ms.users.dto.UserResponseDto;

import lombok.NonNull;

/**
 * 
 * @author mmusatad
 *
 */
public interface UsersService {
	
	UserResponseDto recordUser(UserDto userDto);

	UserResponseDto getUser(String mail);

}
