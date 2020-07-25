package com.xxx.prospect.dto;
import com.google.gson.annotations.SerializedName;

/*
 * @Descripction: Mapeo de datos CustomerData
 * @Autor: OJC04348
 */
public class CustomerDataDTO {
	@SerializedName("Party")
	private CustomerDataPartyDTO Party;
	

	public CustomerDataPartyDTO getParty() {
		return Party;
	}
	public void setParty(CustomerDataPartyDTO party) {
		Party = party;
	}
	

}
