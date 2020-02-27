package com.task.ms.users.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * UserResponseDto: Corresponde a los datos de response del endpoint.
 * Los atributos expuestos en la API se regirán al formato Json solicitado.
 * @author mmusatad
 *
 */

@Data
@Builder
public class UserResponseDto {
	
	private UserDto user;

	private UUID id;

	private LocalDateTime created;

	private LocalDateTime modified;

	@JsonProperty("last_login")
	private LocalDateTime lastLogin;
	
	private UUID token;

	@JsonProperty("isActive")
	private boolean active;
}
