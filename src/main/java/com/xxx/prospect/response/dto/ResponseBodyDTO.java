package com.xxx.prospect.response.dto;

/**
 * Objeto DTO con el body del response erróneo de las peticiones
 * 
 * @author OLM05871
 *
 */
public class ResponseBodyDTO {

	private String codigo;
//	private ResponseMensagenDTO mensagem;
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
