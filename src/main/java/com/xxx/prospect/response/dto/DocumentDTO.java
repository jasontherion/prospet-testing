package com.xxx.prospect.response.dto;
/**
 * Objeto DTO documento (hijo) para el response correcto del servicio
 * @author OLM05871
 *
 */
public class DocumentDTO {
	private String number;

	private String type;

	private String birthdate;

	private String birth_place;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

}
