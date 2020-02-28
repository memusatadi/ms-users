package com.task.ms.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(value = "Fono del usuario", example = "1234567")
	private int number;
	
	@ApiModelProperty(value = "Código de ciudad", example = "1")
	@JsonProperty("citycode")
	private int cityCode;
	
	@ApiModelProperty(value = "Código de país", example = "57")
	@JsonProperty("countrycode")
	private int countryCode;
	
}
