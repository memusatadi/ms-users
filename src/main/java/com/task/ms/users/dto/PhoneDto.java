package com.task.ms.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PhoneDto con los datos telefónicos del cliente
 * @author mmusatad
 * 
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
	
	private int number;
	
	@JsonProperty("citycode")
	private int cityCode;
	
	@JsonProperty("countrycode")
	private int countryCode;
	
}
