package com.xxx.prospect.request.dto;

/**
 * Objeto DTO (hijo) para enviar los headers a servicios externo
 * 
 * @author OLM05871
 *
 * 
 */
public class RequestOutHeadersMessageInfo {

	private String dateTime;

	private String systemId;

	private String originatorName;

	private String originatorType;

	private String terminalId;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}

	

	public String getOriginatorType() {
		return originatorType;
	}

	public void setOriginatorType(String originatorType) {
		this.originatorType = originatorType;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

}
