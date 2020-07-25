package com.xxx.prospect.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Objeto DTO (hijo) para enviar los headers a  servicios externo
 * @author OLM05871
 *
 */
public class RequestOutHeadersRequest {

	@JsonProperty("MessageHeader")
	private RequestOutMessageHeader messageHeader;

	public RequestOutMessageHeader getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(RequestOutMessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	@Override
	public String toString() {
		return "RequestOutHeadersRequest [messageHeader=" + messageHeader + "]";
	}
	
}
