package com.xxx.prospect.response.dto;

/**
 * Objeto DTO para los response erróneo de las peticiones del micro servicio
 * 
 * @author OLM05871
 *
 */
public class ResponseDTO {

//	private String status_code;
//
//	private ResponseBodyDTO body;
//
//	public String getStatus_code() {
//		return status_code;
//	}
//
//	public void setStatus_code(String status_code) {
//		this.status_code = status_code;
//	}
//
//	public ResponseBodyDTO getBody() {
//		return body;
//	}
//
//	public void setBody(ResponseBodyDTO body) {
//		this.body = body;
//	}
	/*
	 * @Override public String toString() { return "ResponseDTO [status_code=" +
	 * status_code + ", body=" + body + "]"; }
	 */

	private String codigo;
	private String mensagem;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
