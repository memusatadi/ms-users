package com.task.users.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.task.ms.users.UsersApplication;
import com.task.ms.users.dto.PhoneDto;
import com.task.ms.users.dto.UserDto;
import com.task.ms.users.dto.UserResponseDto;
import com.task.ms.users.model.PhoneModel;
import com.task.ms.users.model.UserModel;
import com.task.ms.users.repository.UsersRepository;
import com.task.ms.users.service.UsersServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=UsersApplication.class)
public class UsersServiceMockTest {

	@InjectMocks
	private UsersServiceImpl usersService;

	@Mock
	private UsersRepository usersRepository;

	@Test
	public void users_OK() {
		
		List<PhoneDto> phonesListDto = new ArrayList<>();
		
		phonesListDto.add(
				PhoneDto.builder()
				.number(2423455)
				.cityCode(5)
				.countryCode(57)
				.build());
		phonesListDto.add(
				PhoneDto.builder()
				.number(5346675)
				.cityCode(2)
				.countryCode(68)
				.build());
		
		UserDto userDto = UserDto.builder()
				.name("Juan Rodriguez")
				.email("juan@rodriguez.org")
				.password("hunter2")
				.phones(phonesListDto)
				.build();
		
		UserResponseDto userResponseExpected = UserResponseDto.builder()
				.user(userDto)
				.created(LocalDateTime.of(2020, 5, 23, 9, 10, 34))
				.modified(LocalDateTime.of(2020, 5, 23, 9, 10, 34))
				.lastLogin(LocalDateTime.of(2020, 5, 23, 9, 10, 34))
				.token(UUID.fromString("560a8881-a29c-41d5-a716-599999999999"))
				.active(true)
				.build();
		
		UserModel userModel = buildUserModel(userDto,
				UUID.fromString("560a8881-a29c-41d5-a716-599976999599"),
				LocalDateTime.of(2020, 5, 23, 9, 10, 34),
				LocalDateTime.of(2020, 5, 23, 9, 10, 34), 
				LocalDateTime.of(2020, 5, 23, 9, 10, 34),
				UUID.fromString("560a8881-a29c-41d5-a716-599999999999"), 
				true);
				
		Mockito.when(usersRepository.save(userModel)).thenReturn(userModel);
		
		UserResponseDto userResponseActual = usersService.recordUser(userDto);
		
		Assert.assertNotNull(userResponseActual);

		Assert.assertEquals(userResponseExpected.getUser(), userResponseActual.getUser());
		
		Assert.assertEquals(userResponseExpected.getUser().getName(), userResponseActual.getUser().getName());
		
		Assert.assertEquals(userResponseExpected.getUser().getEmail(), userResponseActual.getUser().getEmail());
		
		Assert.assertEquals(userResponseExpected.getUser().getPassword(), userResponseActual.getUser().getPassword());
		
	}

	private UserModel buildUserModel(UserDto userDto, UUID userId, LocalDateTime created, LocalDateTime modified, 
			LocalDateTime lastLogin, UUID token, boolean active, PhoneDto...phones) {
		
		UserModel userModel = new UserModel();
		userModel.setName(userDto.getName());
		userModel.setEmail(userDto.getEmail());
		userModel.setPassword(userDto.getPassword());
		userModel.setPhones(buildPhoneModel(phones, userModel));
		userModel.setUserId(userId.toString());
		userModel.setCreated(created);
		userModel.setModified(modified);
		userModel.setLastLogin(lastLogin);
		userModel.setToken(token.toString());
		userModel.setActive(active);

		return userModel;
	}

	private List<PhoneModel> buildPhoneModel(PhoneDto[] phones, UserModel user) {
		
		List<PhoneModel> phonesModelList = new ArrayList<>();
		
		for (PhoneDto phoneDto : phones) {
		
			PhoneModel phoneModel = new PhoneModel();
			phoneModel.setNumber(phoneDto.getNumber());
			phoneModel.setCityCode(phoneDto.getCityCode());
			phoneModel.setCountryCode(phoneDto.getCountryCode());
			phoneModel.setUser(user);
		
			phonesModelList.add(phoneModel);
		
		};
		
		return phonesModelList;
		
	}
	
	@Test
    public void countUsersByEmail_usersExists() {
		
		ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
			
			UserDto userDto = UserDto.builder()
					.name("Juan Rodriguez")
					.email("juan@rodriguez.org")
					.password("hunter2")
					.build();
			
			long count = 1L;
			
			Mockito.when(usersRepository.countUsersByEmail(userDto.getEmail())).thenReturn(count);
					
			usersService.recordUser(userDto);
			
		});
		
		HttpStatus expectedStatus = HttpStatus.CONFLICT;
        HttpStatus actualStatus = exception.getStatus();
       
        Assert.assertEquals(expectedStatus, actualStatus);
        
	}
	
}
	
