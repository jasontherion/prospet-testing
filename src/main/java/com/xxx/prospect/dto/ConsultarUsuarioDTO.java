package com.xxx.prospect.dto;

/*
 * @Descripction: Mapeo de datos ConsultarUsuario
 * @Autor: OJC04348
 * 
 */

public class ConsultarUsuarioDTO {

	private String codigo;
	private String operacionExitosa;
	private String mensaje;
	private String descripcion;
	private ConsultarUsuarioUsuarioDTO datos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getOperacionExitosa() {
		return operacionExitosa;
	}

	public void setOperacionExitosa(String operacionExitosa) {
		this.operacionExitosa = operacionExitosa;
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

	public ConsultarUsuarioUsuarioDTO getDatos() {
		return datos;
	}

	public void setDatos(ConsultarUsuarioUsuarioDTO datos) {
		this.datos = datos;
	}

}
