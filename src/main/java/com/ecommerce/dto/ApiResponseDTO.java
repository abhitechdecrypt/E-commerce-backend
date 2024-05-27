package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(name = "Response", description = "Schema to hold successful response information")
public class ApiResponseDTO<T> {

	@Schema(description = "Status message in the response")
	private String message;
	@Schema(description = "Status code in the response")
	private String code;
	@Schema(description = "latest data that saved in database")
	private T data;

}
