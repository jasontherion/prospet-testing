package com.xxx.prospect.dto;

import javax.validation.constraints.NotEmpty;

public class ProspectDTO {
	
	@NotEmpty(message="El codigo prospect_id es obligatorio")
	private String prospect_id;

	public String getProspect_id() {
		return prospect_id;
	}

	public void setProspect_id(String prospect_id) {
		this.prospect_id = prospect_id;
	}

}
