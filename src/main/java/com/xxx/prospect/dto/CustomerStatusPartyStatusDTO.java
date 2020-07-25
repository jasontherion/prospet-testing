package com.xxx.prospect.dto;

import com.google.gson.annotations.SerializedName;

public class CustomerStatusPartyStatusDTO {
	
	@SerializedName("partyStatusCode")
	private String partyStatusCode;
	private String statusDesc;
	private String effDt;
	private String closeReason;
	private String restrictType;


	public String getPartyStatusCode() {
		return partyStatusCode;
	}
	public void setPartyStatusCode(String partyStatusCode) {
		this.partyStatusCode = partyStatusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getEffDt() {
		return effDt;
	}
	public void setEffDt(String effDt) {
		this.effDt = effDt;
	}
	public String getCloseReason() {
		return closeReason;
	}
	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}
	public String getRestrictType() {
		return restrictType;
	}
	public void setRestrictType(String restrictType) {
		this.restrictType = restrictType;
	}
	
	

}
