package com.xxx.prospect.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xxx.prospect.request.dto.RequestInHeadersDTO;
import com.xxx.prospect.response.dto.ResponseErrorDTO;
import com.xxx.prospect.response.dto.ResponseOkDTO;
import com.xxx.prospect.service.IProspectService;
import com.xxx.prospect.service.ProspectService;

/*
 * @Descripction: Controladores de acceso a servicios rest
 * @Autor: OJC04348
 * @version: 1
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/prospects/v1")
public class ProspectController {
	Logger logger = LoggerFactory.getLogger(ProspectController.class);

	String[] mensajes = null;
	Gson gson = new Gson(); // Salida json
	JsonParser parser = new JsonParser();

	@Autowired
	private RequestInHeadersDTO requestInHeaders;

	@Autowired
	private IProspectService prospecServiceImpl;

	@Autowired
	private ProspectService prospecService;


	/**
	 * Prueba estado del servicio
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String statusService() {
		Date fecha = new Date();
		String version = "0.0.1";
		return "<h1>Propect Service</h1> <SUB>v" + version
				+ "</SUB> <h4><b style='color:green'>Online<b></h4> <h5 style='color:black'>" + fecha + " </h5>";
	}

	/**
	 * Servicio para consulta el customer status del cliente
	 * 
	 * @param prospect_id, numero de prospect 80149333_CO_CCe
	 * 
	 * @return
	 */
	@GetMapping("/prospects/{prospect_id}")
	public ResponseEntity<?> procesoIdentificarCliente(
			@PathVariable(name = "prospect_id", required = true) String prospect_id,
			@RequestHeader(name = "transaction_id", required = true) String transactionId,
			@RequestHeader(name = "channel-id", required = true) String channelId,
			@RequestHeader(name = "application", required = true) String applicationInfo,
			@RequestHeader(name = "session_id", required = true) String sesionId,
			@RequestHeader(name = "timestamp", required = true) String timestamp) {
		Map<String, Object> respuesta = new HashMap<>();

		try {

			requestInHeaders.setApplication(applicationInfo);
			requestInHeaders.setChannelId(channelId);
			requestInHeaders.setTransactionId(transactionId);
			requestInHeaders.setSesionId(sesionId);
			requestInHeaders.setTimesTamp(timestamp);

			logger.info(" Headers de entrada " + requestInHeaders.toString());

			String outData = prospecService.identificacionCliente(prospect_id);
			

			JsonElement jsonTree = parser.parse(outData);
			ResponseOkDTO res = gson.fromJson(jsonTree, ResponseOkDTO.class);

			respuesta.put("response", res.getBody());
			return new ResponseEntity<>(res.getBody(),  HttpStatus.OK);

		} catch (Exception e) {

			ResponseErrorDTO error = gson.fromJson(e.getMessage(), ResponseErrorDTO.class);

			respuesta.put("codigo", error.getCode());
			respuesta.put("mensagem", e.getMessage());

			return new ResponseEntity<>(respuesta, validStatus(error.getCode()));
		}
	}

	private HttpStatus validStatus(String code) {

		String[] codes = code.split("-");

		switch (codes[0]) {
		case "500":
			return HttpStatus.INTERNAL_SERVER_ERROR;

		case "422":
			return HttpStatus.UNPROCESSABLE_ENTITY;

		case "400":
			return HttpStatus.BAD_REQUEST;

		default:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

	}

	/**
	 * Servicio para consulta el customer status del cliente
	 * 
	 * @param issuedIdentType,  Tipo de identificación del cliente
	 * @param issuedIdentValue, Número de identificación del cliente
	 * @return
	 */
	@GetMapping("/constumerStatus/{issuedIdentType}/{issuedIdentValue}")
	public ResponseEntity<?> customerStatus(@PathVariable int issuedIdentType, @PathVariable String issuedIdentValue) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			String outData = prospecServiceImpl.getCustomerStatus(issuedIdentType, issuedIdentValue);
			mensajes = outData.split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			mensajes = e.getMessage().split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);

		}
	}

	/**
	 * Servicio para consulta el customer data del cliente
	 * 
	 * @param issuedIdentType,  Tipo de identificación del cliente
	 * @param issuedIdentValue, Número de identificación del cliente
	 * @return
	 */
	@GetMapping("/constumerData/{issuedIdentType}/{issuedIdentValue}")
	public ResponseEntity<?> customerData(@PathVariable int issuedIdentType, @PathVariable String issuedIdentValue) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			String outData = prospecServiceImpl.getCustomerData(issuedIdentType, issuedIdentValue);
			mensajes = outData.split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			mensajes = e.getMessage().split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * Servicio para listar en las listas restrictivas el cliente
	 * 
	 * @param nameValue,        nombre del cliente a consultar
	 * @param issuedIdentValue, Número de identificación del cliente
	 * @return
	 */
	@GetMapping(path = "/lista/{issuedIdentValue}")
	@ResponseBody
	public ResponseEntity<?> getList(@PathVariable String issuedIdentValue, String nameValue) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			String outData = prospecServiceImpl.getListConsulta(issuedIdentValue, nameValue);
			mensajes = outData.split("_");
			respuesta.put("code", mensajes[0]);
			respuesta.put("mensaje", mensajes[1]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			mensajes = e.getMessage().split("_");
			respuesta.put("code", mensajes[0]);
			respuesta.put("mensaje", mensajes[1]);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	/**
	 * Servicio para consultar un usuario del sistema
	 * 
	 * @param NumeroDocumento, Numero de documento del usuario
	 * @return
	 */
	@GetMapping("/consultaUsuario/{NumeroDocumento}")
	public ResponseEntity<?> consultarUsuario(@PathVariable String NumeroDocumento) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			String outData = prospecServiceImpl.getConsultarUsuario(NumeroDocumento);
			mensajes = outData.split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			mensajes = e.getMessage().split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	/**
	 * Servicio para consultar usuario Ani del sistema
	 * 
	 * @param IdentificadorProceso, No Identificación del proceso
	 * @param NumeroDocumento,      Número de identificación del cliente
	 * @return
	 */
	@GetMapping("/Any/{IdentificadorProceso}/{NumeroDocumento}")
	public ResponseEntity<?> consultarClienteAni(@PathVariable String IdentificadorProceso,
			@PathVariable String NumeroDocumento) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			String outData = prospecServiceImpl.getConsultarClienteAni(IdentificadorProceso, NumeroDocumento);
			mensajes = outData.split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			mensajes = e.getMessage().split("_");
			respuesta.put("mensaje", mensajes[1]);
			respuesta.put("code", mensajes[0]);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

}
