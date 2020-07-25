package com.xxx.prospect.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Objeto DTO (Padre) para enviar los headers a  servicios externos
 * @author OLM05871
 *
 */
public class RequestOutHeadersDTO {

	
	@JsonProperty("HeaderRequest")
	private RequestOutHeadersRequest headerRequest;

	public RequestOutHeadersRequest getHeaderRequest() {
		return headerRequest;
	}

	public void setHeaderRequest(RequestOutHeadersRequest headerRequest) {
		this.headerRequest = headerRequest;
	}

	@Override
	public String toString() {
		return "RequestOutHeadersDTO [headerRequest=" + headerRequest + "]";
	}
}
