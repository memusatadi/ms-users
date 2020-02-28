package com.task.ms.users.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * Se controla que los mensajes de error que responde el Controller sean comprensibles por el usuario
 * y tengasn el formato definido.
 * Clase que intercepta las excepciones propagadas por el Controller
 * @author mmusatad
 *
 */
@ControllerAdvice
public class UsersErrorHandler {

	//Maneja excepción de negocio. Responde con el status de la excepción.
	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<ErrorDto> handleUsersException(ResponseStatusException ex) {
		
		ErrorDto err = ErrorDto.builder()
				.message(ex.getMessage())
				.build();
		
		return new ResponseEntity<ErrorDto>(err, new HttpHeaders(), ex.getStatus());
	}
	
	//Maneja excepciones para el caso de atributos que no siguen el formato especificado
	//según Pattern o Email.
	@ExceptionHandler({ MethodArgumentNotValidException.class, InvalidFormatException.class })
	public ResponseEntity<ErrorDto> handleNotValidException(Exception ex) {
		
		ErrorDto err = ErrorDto.builder()
				.message(ex.getMessage())
				.build();
		
		return new ResponseEntity<ErrorDto>(err, new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
}
