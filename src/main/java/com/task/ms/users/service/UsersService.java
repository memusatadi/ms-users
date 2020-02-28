package com.task.ms.users.service;

import com.task.ms.users.dto.UserDto;
import com.task.ms.users.dto.UserResponseDto;

/**
 * 
 * @author mmusatad
 *
 */
public interface UsersService {
	
	UserResponseDto recordUser(UserDto userDto);

	UserResponseDto getUser(String mail);

}
