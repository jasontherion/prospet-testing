package com.xxx.prospect.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Objeto DTO (hijo) para enviar los headers a servicios externo
 * 
 * @author OLM05871
 *
 */
public class RequestOutMessageHeader {

	@JsonProperty("MessageKey")
	private RequestOutHeadersMessageKey messageKey;

	@JsonProperty("MessageInfo")
	private RequestOutHeadersMessageInfo messageInfo;

	public RequestOutHeadersMessageKey getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(RequestOutHeadersMessageKey messageKey) {
		this.messageKey = messageKey;
	}

	public RequestOutHeadersMessageInfo getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(RequestOutHeadersMessageInfo messageInfo) {
		this.messageInfo = messageInfo;
	}

	@Override
	public String toString() {
		return "RequestOutMessageHeader [messageKey=" + messageKey + ", messageInfo=" + messageInfo + "]";
	}

}
