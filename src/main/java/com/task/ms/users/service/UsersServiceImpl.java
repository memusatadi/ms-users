package com.task.ms.users.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.task.ms.users.dto.PhoneDto;
import com.task.ms.users.dto.UserDto;
import com.task.ms.users.dto.UserResponseDto;
import com.task.ms.users.model.PhoneModel;
import com.task.ms.users.model.UserModel;
import com.task.ms.users.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Value("${message.userExists}")
	private String messageUserExists;

	@Override
	public UserResponseDto recordUser(UserDto userDto) {

		long count = usersRepository.countUsersByEmail(userDto.getEmail());
		
		if (count > 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, messageUserExists);
		}
		
		UUID uid = UUID.randomUUID();
		UserModel userModel = new UserModel();
		userModel.setName(userDto.getName());
		userModel.setEmail(userDto.getEmail());
		userModel.setPassword(userDto.getPassword());
		userModel.setPhones(buildPhoneModel(userDto.getPhones(), userModel));
		userModel.setUserId(uid.toString());
		userModel.setCreated(LocalDateTime.now());
		userModel.setModified(LocalDateTime.now());
		userModel.setLastLogin(LocalDateTime.now());
		userModel.setToken(UUID.randomUUID().toString());
		userModel.setActive(true);

		usersRepository.save(userModel);

		UserResponseDto userResponse = UserResponseDto.builder()
				.user(userDto)
				.id(uid).created(userModel.getCreated())
				.modified(userModel.getModified())
				.lastLogin(userModel.getLastLogin())
				.token(UUID.fromString(userModel.getToken()))
				.active(userModel.isActive())
				.build();

		return userResponse;

	}
	
	private List<PhoneModel> buildPhoneModel(List<PhoneDto> phones, UserModel user) {

		List<PhoneModel> phonesModelList = new ArrayList<>();

		for (PhoneDto phoneDto : phones) {

			PhoneModel phoneModel = new PhoneModel();
			phoneModel.setNumber(phoneDto.getNumber());
			phoneModel.setCityCode(phoneDto.getCityCode());
			phoneModel.setCountryCode(phoneDto.getCountryCode());
			phoneModel.setUser(user);

			phonesModelList.add(phoneModel);

		}

		return phonesModelList;

	}

	@Override
	public UserResponseDto getUser(String email) {
		
		UserModel userModel = usersRepository.findUserByEmail(email);
		
		if (userModel == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe usuario para el e-mail indicado");
		}
		
		UserResponseDto userResponse = UserResponseDto.builder()
				.user(buildUserDto(userModel))
				.id(UUID.fromString(userModel.getUserId()))
				.created(userModel.getCreated())
				.modified(userModel.getModified())
				.lastLogin(userModel.getLastLogin())
				.token(UUID.fromString(userModel.getToken()))
				.active(userModel.isActive())
				.build();
		
		return userResponse;
	}

	private UserDto buildUserDto(UserModel userModel) {

		UserDto userDto = UserDto.builder()
				.name(userModel.getName())
				.email(userModel.getEmail())
				.password(userModel.getPassword())
				.phones(buildPhoneDto(userModel.getPhones(), userModel.getUserId()))
				.build();
		
		return userDto;
	}

	private List<PhoneDto> buildPhoneDto(List<PhoneModel> phones, String userId) {

		List<PhoneDto> phonesDtoList = new ArrayList<>();

		for (PhoneModel phoneModel : phones) {

			PhoneDto phoneDto = PhoneDto.builder()
					.number(phoneModel.getNumber())
					.cityCode(phoneModel.getCityCode())
					.countryCode(phoneModel.getCountryCode())
					.build();
			
			phonesDtoList.add(phoneDto);

		}

		return phonesDtoList;
		
	}
}
