package com.xxx.prospect.service;

public interface IProspectService {
	
	public String getCustomerData(int issuedIdentType, String issuedIdentValue);
	
	public String  getCustomerStatus(int issuedIdentType, String issuedIdentValue);

	public String getListConsulta(String issuedIdentValue, String nameValue);

	public String getConsultarUsuario(String NumeroDocumento);

	public String getConsultarClienteAni(String IdentificadorProceso, String NumeroDocumento );

}
