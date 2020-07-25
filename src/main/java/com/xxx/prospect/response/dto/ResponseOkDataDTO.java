package com.xxx.prospect.response.dto;

/**
 * Objeto DTO (hijo) data para el response correcto del servicio
 * 
 * @author OLM05871
 *
 */
public class ResponseOkDataDTO {

	private String prospect_id;
	private String first_name;
	private String second_name;
	private String first_surname;
	private String second_surname;
	private String gender;

	private DocumentDTO[] document;

	public String getProspect_id() {
		return prospect_id;
	}

	public void setProspect_id(String prospect_id) {
		this.prospect_id = prospect_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getFirst_surname() {
		return first_surname;
	}

	public void setFirst_surname(String first_surname) {
		this.first_surname = first_surname;
	}

	public String getSecond_surname() {
		return second_surname;
	}

	public void setSecond_surname(String second_surname) {
		this.second_surname = second_surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public DocumentDTO[] getDocument() {
		return document;
	}

	public void setDocument(DocumentDTO[] document) {
		this.document = document;
	}

}
