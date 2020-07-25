package com.xxx.prospect.response.dto;


/**
 * Objeto DTO para los response correctos de las peticiones
 * @author OLM05871
 *
 */
public class ResponseOkBodyDTO {

	private ResponseOkDataDTO data;

	public ResponseOkDataDTO getData() {
		return data;
	}

	public void setData(ResponseOkDataDTO data) {
		this.data = data;
	}

}
