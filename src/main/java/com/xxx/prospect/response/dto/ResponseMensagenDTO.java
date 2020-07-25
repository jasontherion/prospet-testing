package com.xxx.prospect.response.dto;

/**
 * Objeto Mensajem DTO para los response erróneo de las peticiones
 * 
 * @author OLM05871
 *
 */
public class ResponseMensagenDTO {

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
