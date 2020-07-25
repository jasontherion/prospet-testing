package com.xxx.prospect.dto;

public class StatusRequestDTO {

	private Integer statusCode;
	private String serverStatusCode;
	private String severity;
	private String statusDesc;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getServerStatusCode() {
		return serverStatusCode;
	}

	public void setServerStatusCode(String serverStatusCode) {
		this.serverStatusCode = serverStatusCode;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
/*
	@Override
	public String toString() {
		return "StatusRequestDTO [statusCode=" + statusCode + ", serverStatusCode=" + serverStatusCode + ", severity="
				+ severity + ", statusDesc=" + statusDesc + "]";
	}
*/
	
	
}
