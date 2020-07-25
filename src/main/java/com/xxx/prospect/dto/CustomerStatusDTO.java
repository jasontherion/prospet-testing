package com.xxx.prospect.dto;


/*
 * @Descripction: Mapeo de datos CustomerStatus
 * @Autor: OJC04348
 */
public class CustomerStatusDTO {

	private CustomerStatusStatusDTO Status;
	private  CustomerStatusPartyStatusDTO PartyStatus;
	

	public CustomerStatusStatusDTO getStatus() {
		return Status;
	}
	public void setStatus(CustomerStatusStatusDTO status) {
		Status = status;
	}
	public CustomerStatusPartyStatusDTO getPartyStatus() {
		return PartyStatus;
	}
	public void setPartyStatus(CustomerStatusPartyStatusDTO partyStatus) {
		PartyStatus = partyStatus;
	}
	/*
	@Override
	public String toString() {
		return "CustomerStatusDTO [PartyStatus=" + PartyStatus + ", Status=" + Status + " , StatusR=" + StatusR + "]";
	}
	*/

	


}
