package com.task.ms.users.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ErrorDto {

	@NonNull
	private String message;
}
