package com.xxx.prospect.test.controllers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.xxx.prospect.controllers.ProspectController;
import com.xxx.prospect.dto.ProspectDTO;
import com.xxx.prospect.response.dto.DocumentDTO;
import com.xxx.prospect.response.dto.ResponseErrorDTO;
import com.xxx.prospect.response.dto.ResponseOkBodyDTO;
import com.xxx.prospect.response.dto.ResponseOkDTO;
import com.xxx.prospect.response.dto.ResponseOkDataDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProcesoIdentificarCliente {

	Gson gson = new Gson(); // Salida json

	@Autowired
	ProspectController rest;
	
	@Autowired
	ResponseErrorDTO responseErrorDTO;

	private Validator validator;

	private String transactionId =  "910cf3c1-99d1-40e6-a8e2-8423b120A010";
	private String channelId = "131";
	private String applicationInfo = "Portal PN";
	private String sesionId = "SesionId";
	private String timestamp = "2019-05-28T11:50:09";

	String prospect_id = null;
	String first_name = "LUIS";
	String second_name = "FERNANDO";
	String first_surname = "MORALES";
	String second_surname = "DIAZ";
	String gender = "";

	ResponseOkDataDTO data = new ResponseOkDataDTO();
	ResponseOkDTO responseOK = new ResponseOkDTO();
	ResponseOkBodyDTO responseBodyOK = new ResponseOkBodyDTO();

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		// Variables
		data.setFirst_name(first_name);
		data.setSecond_name(second_name);
		data.setFirst_surname(first_surname);
		data.setSecond_surname(second_surname);
		data.setGender(gender);
		
	}
	
	/*
	 * Resultado esperado
	 * 
	 * 
	 */

	@Test
	public void test() {
		prospect_id = "1030615871_CO_CC";
		data.setProspect_id(prospect_id);
		DocumentDTO[] document = { this.do1Test() };

		data.setDocument(document);
		responseBodyOK.setData(data);
		responseOK.setBody(responseBodyOK);

		ResponseOkBodyDTO expecteds = responseOK.getBody();
		ResponseEntity<?> datos = rest.procesoIdentificarCliente(prospect_id, transactionId, channelId,
				applicationInfo, sesionId, timestamp);

		String esperado = gson.toJson(expecteds);
		String resultado = gson.toJson(datos.getBody());

		assertEquals(esperado, resultado);

	}
	
	/*
	 * Validacion de parametro faltante
	 * 
	 * 
	 */

//	@Test
//	public void testParametrosRequeridos() {
//		ProspectDTO prospectDTO = new ProspectDTO();
//		prospectDTO.setProspect_id(prospect_id);		
//		Set<ConstraintViolation<ProspectDTO>> violations = validator.validate(prospectDTO);
//		assertFalse(violations.isEmpty());
//
//	}
	
	/*
	 * Resultado esperado
	 * 
	 * 
	 */
	
//	@Test
//	public void testParametrosCedula() {
//		prospect_id = "1030615871_CO_TY";
//		data.setProspect_id(prospect_id);
//		DocumentDTO[] document = { this.do1Test() };
//
//		data.setDocument(document);
//		Map<String, Object> respuesta = new HashMap<>();
//		respuesta.put("codigo", "422-12");
//		respuesta.put("mensagem", "{\"code\":\"422-12\",\"message\":\"No se encontro el tipo de cédula\"}");
//
//		ResponseEntity<?> datos = rest.procesoIdentificarCliente(prospect_id, transactionId, channelId,
//				applicationInfo, sesionId, timestamp);
//
//		String esperado = gson.toJson(respuesta);
//		String resultado = gson.toJson(datos.getBody());
//
//		assertEquals(esperado, resultado);
//		
//		
//	}
	
	/*
	 * Validacion de parametro faltante
	 * 
	 * 
	 */
	
	
//	@Test
//	public void testParametrosFormato() {
//		prospect_id = "_CO_CC";
//		data.setProspect_id(prospect_id);
//		DocumentDTO[] document = { this.do1Test() };
//
//		data.setDocument(document);
//		Map<String, Object> respuesta = new HashMap<>();
//		respuesta.put("codigo", "400");
//		respuesta.put("mensagem", "{\"code\":\"400\",\"message\":\"Parametro inválido ####### XX XX\"}");
//
//		ResponseEntity<?> datos = rest.procesoIdentificarCliente(prospect_id, transactionId, channelId,
//				applicationInfo, sesionId, timestamp);
//
//		String esperado = gson.toJson(respuesta);
//		String resultado = gson.toJson(datos.getBody());
//
//		assertEquals(esperado, resultado);
//		
//		
//	}

	private DocumentDTO do1Test() {
		DocumentDTO do1 = new DocumentDTO();
		String birth_place = "CO";
		String birthdate = "";
		String type = "CO-CC";
		String number = "1030615871";

		do1.setBirth_place(birth_place);
		do1.setBirthdate(birthdate);
		do1.setNumber(number);
		do1.setType(type);
		return do1;
	}
	
}
