package com.xxx.prospect.response.dto;

import java.util.List;

public class ResponseDataDTO {
	private String prospect_id;

	 private List<DocumentDTO> document;

	public String getProspect_id() {
		return prospect_id;
	}

	public void setProspect_id(String prospect_id) {
		this.prospect_id = prospect_id;
	}

	public List<DocumentDTO> getDocument() {
		return document;
	}

	public void setDocument(List<DocumentDTO> document) {
		this.document = document;
	}


}
