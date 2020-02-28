package com.task.ms.users.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class UsersErrorHandler {

	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<ErrorDto> handleUsersException(ResponseStatusException ex) {
		
		ErrorDto err = ErrorDto.builder()
				.message(ex.getMessage())
				.build();
		
		return new ResponseEntity<ErrorDto>(err, new HttpHeaders(), ex.getStatus());
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorDto> handleNotValidException(MethodArgumentNotValidException ex) {
		
		ErrorDto err = ErrorDto.builder()
				.message(ex.getMessage())
				.build();
		
		return new ResponseEntity<ErrorDto>(err, new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
}
