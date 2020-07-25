package com.xxx.prospect.request.dto;

/**
 * Objeto DTO (hijo) para enviar los headers a servicios externo
 * 
 * @author OLM05871
 * 
 */
public class RequestOutHeadersMessageKey {

	private String integrationId;

	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	@Override
	public String toString() {
		return "RequestOutHeadersMessageKey [integrationId=" + integrationId + "]";
	}
	
}
