package com.xxx.prospect.dto;

/*
 * @Descripction: Mapeo de datos ConsultarUsuarioAni
 * @Autor: OJC04348
 * 
 */

public class ConsultarClienteAniDTO {

	private String codigo;
	private String mensaje;
	private String descripcion;
	private String operacionExitosa;

	private ConsultarClienteAniANInfoDTO datos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOperacionExitosa() {
		return operacionExitosa;
	}

	public void setOperacionExitosa(String operacionExitosa) {
		this.operacionExitosa = operacionExitosa;
	}

	public ConsultarClienteAniANInfoDTO getDatos() {
		return datos;
	}

	public void setDatos(ConsultarClienteAniANInfoDTO datos) {
		this.datos = datos;
	}

}
