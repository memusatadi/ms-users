package com.task.ms.users.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * UserDto con los datos de usuario ingresados en el request. 
 * Se insertan expresiones regulares para el password para controlar que se componga de 1 Mayúscula, 2 números y minúsculas.
 * Se controla que cada atributo no sea nulo y que el formato del mail sea válido.
 * @author mmusatad
 * 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	/*
	 * 
	 */
	private static final String UPPER = "(?=[^A-Z]*[A-Z][^A-Z]*$)";
	
	private static final String LOWER = "(?=.*[a-z])";
	
	private static final String NUM = "(?=[^0-9]*[0-9][^0-9]*[0-9][^0-9]*$)";
	
	private static final String LEN = ".{8,16}";
	
	@NonNull
	private String name;
	
	@NonNull
	@Email(message = "El e-mail debe ser válido.")
	private String email;
	
	@NonNull
	@Pattern(regexp = UPPER + LOWER + NUM + LEN)
	private String password;
	
	private List<PhoneDto> phones;
	
}
