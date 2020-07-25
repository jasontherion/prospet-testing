package com.xxx.prospect.dto;


import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CustomerDataPartyDTO {
	private Object PartyKey;
	private Object PartyStatus;
	private Object PersonPartyInfo;
	@SerializedName("PartyAdditionalInfoList")
	private List<CustomerDataPartitinAdditionInfoDTO> PartyAdditionalInfoList;
	
	
	
	public List<CustomerDataPartitinAdditionInfoDTO> getPartyAdditionalInfoList() {
		return PartyAdditionalInfoList;
	}
	public void setPartyAdditionalInfoList(List<CustomerDataPartitinAdditionInfoDTO> partyAdditionalInfoList) {
		PartyAdditionalInfoList = partyAdditionalInfoList;
	}
	public Object getPartyKey() {
		return PartyKey;
	}
	public void setPartyKey(Object partyKey) {
		PartyKey = partyKey;
	}
	public Object getPartyStatus() {
		return PartyStatus;
	}
	public void setPartyStatus(Object partyStatus) {
		PartyStatus = partyStatus;
	}
	public Object getPersonPartyInfo() {
		return PersonPartyInfo;
	}
	public void setPersonPartyInfo(Object personPartyInfo) {
		PersonPartyInfo = personPartyInfo;
	}

	



}
