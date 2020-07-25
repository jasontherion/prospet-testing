package com.xxx.prospect.request.dto;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Objeto DTO para los headers que recibe el micro servicio
 * 
 * @author OLM05871
 *
 */
@AutoConfigurationPackage
@ConditionalOnBean
public class RequestInHeadersDTO {

	@JsonProperty("transaction_id ")
	private String transactionId;
	@JsonProperty("application ")
	private String application;
	@JsonProperty("channel-id")
	private String channelId;
	@JsonProperty("session_id ")
	private String sesionId;
	
	private String timesTamp;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transaction_id) {
		this.transactionId = transaction_id;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getSesionId() {
		return sesionId;
	}

	public void setSesionId(String sesionId) {
		this.sesionId = sesionId;
	}
	
	public String getTimesTamp() {
		return timesTamp;
	}

	public void setTimesTamp(String timesTamp) {
		this.timesTamp = timesTamp;
	}

	@Override
	public String toString() {
		return "RequestInHeadersDTO [transactionId=" + transactionId + ", application=" + application + ", channelId="
				+ channelId + ", sesionId=" + sesionId + ", timesTamp=" + timesTamp + "]";
	}


}
