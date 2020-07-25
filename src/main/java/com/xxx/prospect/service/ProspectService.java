package com.xxx.prospect.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xxx.prospect.response.dto.ResponseErrorDTO;
import com.xxx.prospect.response.dto.ResponseOkDataDTO;
import com.xxx.prospect.service.implement.ProspectServiceImpl;
import com.xxx.prospect.utilitarios.excep.CustomRuntimeException;
import com.xxx.prospect.utilitarios.gener.HelperUtil;

/*
 * @Descripction: Servicio de generación de respuesta de procesos validar cliente 
 * @Autor: OJC04348
 */
@Service
public class ProspectService {
	Logger logger = LoggerFactory.getLogger(ProspectServiceImpl.class);// Manejo de errores
	@Autowired
	private IProspectService prospectServiceImpl;

	@Autowired
	private ResponseOkDataDTO responseOkData;

	@Autowired
	private ResponseErrorDTO responseErrorDTO;

	Gson gson = new Gson(); // Salida json

	public String identificacionCliente(String prospect_id) {

		System.out.println(prospect_id.length());
		String errorResponse = null;
		String outProcess = null;
		try {

			String pattern = "[0-9A-Za-z]+_+[A-Za-z]{2,2}+_+[A-Za-z]{2,2}";

			if (!HelperUtil.isValidPattern(prospect_id, pattern))
				throw new CustomRuntimeException("400_Parametro inválido ####### XX XX");

			String[] vectProspect = prospect_id.split("_");

			int codigoCedula = 0;
			String tipoDocumento = vectProspect[2].toUpperCase();

			switch (tipoDocumento) {
			case "CC":
				codigoCedula = 1;
				break;
			case "CE":
				codigoCedula = 2;
				break;
			case "RC":
				codigoCedula = 3;
				break;
			case "TI":
				codigoCedula = 4;
				break;
			case "PA":
				codigoCedula = 5;
				break;
			default:
				throw new CustomRuntimeException("422-12_No se encontro el tipo de cédula");
			}

			/*
			 * Proceso validación cliente [1 - CustomerStatus Cliente fénix] [2 -
			 * CustomerData - CLiente no sea PEP] [3 - Listas restrictivas] [4 - Consulta
			 * usuario] [5 - Consulta usuario ANI]
			 */

			prospectServiceImpl.getCustomerStatus(codigoCedula, vectProspect[0]);
			prospectServiceImpl.getCustomerData(codigoCedula, vectProspect[0]);
			prospectServiceImpl.getListConsulta(vectProspect[0], null);
			prospectServiceImpl.getConsultarClienteAni("1", vectProspect[0]);
			prospectServiceImpl.getConsultarUsuario(vectProspect[0]);

			String genero = (responseOkData.getGender() == null) ? "" : responseOkData.getGender();
			String gender = "\"gender\": \"" + genero + "\",";

			String fechaNacimiento = "";
			if (responseOkData.getDocument() != null) {
				fechaNacimiento = (responseOkData.getDocument()[0].getBirthdate() != null)
						? responseOkData.getDocument()[0].getBirthdate()
						: "";
			}
			String[] place = vectProspect[1].split("-");

			outProcess = " { \"status_code\": 200,\"body\": {\"data\": {\"prospect_id\": \"" + prospect_id
					+ "\",  \"first_name\": \"" + responseOkData.getFirst_name() + "\",  \"second_name\": \""
					+ responseOkData.getSecond_name() + "\", \"first_surname\": \"" + responseOkData.getFirst_surname()
					+ "\", \"second_surname\": \"" + responseOkData.getSecond_surname() + "\",  " + gender
					+ " \"document\":[{\"number\":\"" + vectProspect[0] + "\",\"type\": \"" + vectProspect[1] + "-"
					+ vectProspect[2] + "\", \"birthdate\":\"" + fechaNacimiento + "\", \"birth_place\":\"" + place[0]
					+ "\" }]}}}";

			return outProcess;
		} catch (Exception e) {
			logger.error("Error el proceso de validación" + e.getMessage());

			String[] mensajes = e.getMessage().split("_");
			responseErrorDTO.setCode(mensajes[0]);
			responseErrorDTO.setMessage(mensajes[1]);
			throw new CustomRuntimeException(gson.toJson(responseErrorDTO));

		}
	}

}
