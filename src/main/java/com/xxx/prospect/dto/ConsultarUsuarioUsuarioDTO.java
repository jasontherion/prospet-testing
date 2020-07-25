package com.xxx.prospect.dto;

import com.google.gson.annotations.SerializedName;

/**
 * ObjetoDTO hijo del ojeto ConsultarUsuarioDTO
 * @author OLM05871
 *
 */
public class ConsultarUsuarioUsuarioDTO {

	private String IdCliente;
	private String NumeroDocumento;
	private String PrimerApellido;
	private String SegundoApellido;
	private String PrimerNombre;
	private String SegundoNombre;
	private String NombreCompleto;
	private String Sexo;
	private String FechaNacimiento;
	private String RH;
	private String VersionCedula;
	private String TipoDedo1;
	private String TipoDedo2;
	private String NumeroTarjeta;
	private String FechaCreacion;
	@SerializedName("EnroladoDactilar")
	private String EnroladoDactilar;
	private String EnroladoFacial;
	private String TemplateDactilar;

	public String getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}

	public String getNumeroDocumento() {
		return NumeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		NumeroDocumento = numeroDocumento;
	}

	public String getPrimerApellido() {
		return PrimerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		PrimerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return SegundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		SegundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return PrimerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		PrimerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return SegundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		SegundoNombre = segundoNombre;
	}

	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getRH() {
		return RH;
	}

	public void setRH(String rH) {
		RH = rH;
	}

	public String getVersionCedula() {
		return VersionCedula;
	}

	public void setVersionCedula(String versionCedula) {
		VersionCedula = versionCedula;
	}

	public String getTipoDedo1() {
		return TipoDedo1;
	}

	public void setTipoDedo1(String tipoDedo1) {
		TipoDedo1 = tipoDedo1;
	}

	public String getTipoDedo2() {
		return TipoDedo2;
	}

	public void setTipoDedo2(String tipoDedo2) {
		TipoDedo2 = tipoDedo2;
	}

	public String getNumeroTarjeta() {
		return NumeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		NumeroTarjeta = numeroTarjeta;
	}

	public String getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public String getEnroladoDactilar() {
		return EnroladoDactilar;
	}

	public void setEnroladoDactilar(String enroladoDactilar) {
		EnroladoDactilar = enroladoDactilar;
	}

	public String getEnroladoFacial() {
		return EnroladoFacial;
	}

	public void setEnroladoFacial(String enroladoFacial) {
		EnroladoFacial = enroladoFacial;
	}

	public String getTemplateDactilar() {
		return TemplateDactilar;
	}

	public void setTemplateDactilar(String templateDactilar) {
		TemplateDactilar = templateDactilar;
	}

}
