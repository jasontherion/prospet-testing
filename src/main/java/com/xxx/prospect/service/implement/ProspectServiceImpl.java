package com.xxx.prospect.service.implement;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.xxx.prospect.dto.ConsultarClienteAniDTO;
import com.xxx.prospect.dto.ConsultarUsuarioDTO;
import com.xxx.prospect.dto.CustomerDataDTO;
import com.xxx.prospect.dto.CustomerDataPartitinAdditionInfoDTO;
import com.xxx.prospect.dto.CustomerStatusDTO;
import com.xxx.prospect.dto.ListConsultaDTO;
import com.xxx.prospect.request.dto.RequestInHeadersDTO;
import com.xxx.prospect.request.dto.RequestOutHeadersDTO;
import com.xxx.prospect.request.dto.RequestOutHeadersMessageInfo;
import com.xxx.prospect.request.dto.RequestOutHeadersMessageKey;
import com.xxx.prospect.request.dto.RequestOutHeadersRequest;
import com.xxx.prospect.request.dto.RequestOutMessageHeader;
import com.xxx.prospect.response.dto.DocumentDTO;
import com.xxx.prospect.response.dto.ResponseOkDataDTO;
import com.xxx.prospect.service.IProspectService;
import com.xxx.prospect.utilitarios.excep.CustomRuntimeException;
import com.xxx.prospect.utilitarios.gener.ConstantesClienteService;

@Service
public class ProspectServiceImpl implements IProspectService {

	@Autowired
	private RestTemplate apiRestClient;

	@Autowired
	private ResponseOkDataDTO responseOkData;
	
	@Autowired
	RequestInHeadersDTO requestInHeaders;
	
	@Value("${URLSERVICECUSTOMERSTATUS}")
	private String URLSERVICECUSTOMERSTATUS;
	
	@Value("${URLSERVICECUSTOMERDATA}")
	private String URLSERVICECUSTOMERDATA;
	
	@Value("${URLSERVICELISTA}")
	private String URLSERVICELISTA;
	
	@Value("${URLSERVICEANY}")
	private String URLSERVICEANY;
	
	@Value("${URLSERVICECONSULTARUSUARIO}")
	private String URLSERVICECONSULTARUSUARIO;

	Gson gson = new Gson(); // Salida json

	String timeStamp = (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime()))
			.replace("_", "T");

	ResponseEntity<String> resultadoData = null;

	String rqUIDRequest = "3f5b0688-69ca-4680-99d0-7c9565fe8066";
	Logger logger = LoggerFactory.getLogger(ProspectServiceImpl.class);// Manejo de errores

	/*
	 * @Descripction: Consulta el servicio costumerStatus
	 * 
	 * @Autor: OJC04348
	 */
	@Override
	public String getCustomerStatus(int issuedIdentType, String issuedIdentValue) {
		CustomerStatusDTO datosFinal = null;

	
		String msgRqHdr = "";
		
		try {
			msgRqHdr = this.createMsgRqHdr();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept-Encoding", " gzip,deflate");
		headers.add("MsgRqHdr", msgRqHdr);
		headers.add("RqUID", rqUIDRequest);
		HttpEntity<String> customHeaders = new HttpEntity<String>("parameters", headers);

		try {
			String url = URLSERVICECUSTOMERSTATUS;
			String apiUrl = url + "issuedIdentType=" + issuedIdentType + "&issuedIdentValue=" + issuedIdentValue;
			String[] codeValid = ConstantesClienteService.CLienteStatus.STATUSVALPARTYSTATUS.split("_");

			try {

				String respEntity = apiRestClient.exchange(apiUrl, HttpMethod.GET, customHeaders, String.class)
						.getBody();
				datosFinal = gson.fromJson(respEntity, CustomerStatusDTO.class);

			} catch (RestClientException e) {
				logger.info("Error petición REST a " + apiUrl);
				if (e.getRootCause() instanceof SocketTimeoutException) {
					logger.error("SocketTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteStatus.STATUSCODEERROR + "Time out");
				} else if (e.getRootCause() instanceof ConnectException) {
					logger.error("ConnectTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteStatus.STATUSCODEERROR + "Conexion error");
				} else if (e instanceof HttpStatusCodeException) {
					logger.error("Error por codigos HTTP", e);
					HttpStatus status = ((HttpStatusCodeException) e).getStatusCode();
					if (status.value() == 500) {
						throw new CustomRuntimeException(ConstantesClienteService.CLienteStatus.STATUSCODEERROR500,
								ConstantesClienteService.CLienteStatus.STATUSMESAJEERROR500);
					} else {
						String errorResponse = ((HttpStatusCodeException) e).getResponseBodyAsString();
						logger.error("Error servicio externo CustomerStatus: " + errorResponse);
						datosFinal = gson.fromJson(errorResponse, CustomerStatusDTO.class);
					}

				}
			}

			if (datosFinal != null) {

				if (datosFinal.getStatus().getStatusCode() == null) {

					throw new CustomRuntimeException(ConstantesClienteService.CLienteStatus.STATUSCODEERROR
							+ ConstantesClienteService.CLienteStatus.STATUSMENSAJEERRORRECHAZADO);
				}

				if (datosFinal.getStatus().getStatusCode() == ConstantesClienteService.CLienteStatus.STATUSVALCODE
						&& (datosFinal.getPartyStatus() == null
								|| datosFinal.getPartyStatus().getPartyStatusCode().equals(codeValid[0])
								|| datosFinal.getPartyStatus().getPartyStatusCode().equals(codeValid[1])
								|| datosFinal.getPartyStatus().getPartyStatusCode().equals(codeValid[2]))) {

					return ConstantesClienteService.CLienteStatus.STATUSCODEOK
							+ ConstantesClienteService.CLienteStatus.STATUSOKMENSAJE;

				} else {

					throw new CustomRuntimeException(ConstantesClienteService.CLienteStatus.STATUSCODEERROR
							+ ConstantesClienteService.CLienteStatus.STATUSMENSAJEERRORRECHAZADO);
				}

			}

		} catch (Exception e) {// Error del servidor
			logger.error("Salida de esto ya " + e.getMessage());
			logger.error(e.getMessage());
			throw new CustomRuntimeException(e.getMessage());
		}

		return issuedIdentValue;
	}

	/*
	 * @Descripction: Consulta el servicio costumerData
	 * 
	 * @Autor: OJC04348
	 */
	@Override
	public String getCustomerData(int issuedIdentType, String issuedIdentValue) {

		CustomerDataDTO datosFinal = null;
		
		String msgRqHdr = "";
		
		try {
			msgRqHdr = this.createMsgRqHdr();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("MsgRqHdr", msgRqHdr);
		headers.add("RqUID", "3f5b0688-69ca-4680-99d0-7c9565fe8042");
		HttpEntity<String> customHeaders = new HttpEntity<String>("parameters", headers);

		try {

			String url = URLSERVICECUSTOMERDATA;
			String apiUrl = url + "issuedIdentType=" + issuedIdentType + "&issuedIdentValue=" + issuedIdentValue;

			try {

				String respEntity = apiRestClient.exchange(apiUrl, HttpMethod.GET, customHeaders, String.class)
						.getBody();
				datosFinal = gson.fromJson(respEntity, CustomerDataDTO.class);

			} catch (RestClientException e) {
				logger.info("Error petición REST a " + apiUrl);
				if (e.getRootCause() instanceof SocketTimeoutException) {
					logger.error("SocketTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteStatus.STATUSCODEERROR + "Time out");
				} else if (e.getRootCause() instanceof ConnectException) {
					logger.error("ConnectTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteStatus.STATUSCODEERROR + "Conexion error");
				} else if (e instanceof HttpStatusCodeException) {
					logger.error("Error por codigos HTTP", e);
					HttpStatus status = ((HttpStatusCodeException) e).getStatusCode();
					if (status.value() == 500) {
						throw new CustomRuntimeException(ConstantesClienteService.CLienteStatus.STATUSCODEERROR500,
								ConstantesClienteService.CLienteStatus.STATUSMESAJEERROR500);
					} else {
						String errorResponse = ((HttpStatusCodeException) e).getResponseBodyAsString();
						logger.error("Error servicio externo CustomerStatus: " + errorResponse);
						// datosFinal = gson.fromJson(errorResponse, CustomerDataDTO.class);
					}

				}
			}

			if (datosFinal != null) {

				if (datosFinal.getParty().getPartyAdditionalInfoList() != null) {
					for (CustomerDataPartitinAdditionInfoDTO obj : datosFinal.getParty().getPartyAdditionalInfoList()) {
						if (obj.getAdditionalInfoId() == ConstantesClienteService.CLienteData.ADDITIONALINFOID) {

							if (obj.getAdditionalInfoValue().toUpperCase()
									.equals(ConstantesClienteService.CLienteData.ADDITIONALINFOVALUE))
								return ConstantesClienteService.CLienteData.STATUSCODEOK
										+ ConstantesClienteService.CLienteData.STATUSOKMENSAJE;
						}

					}
				} else {
					return ConstantesClienteService.CLienteData.STATUSCODEOK
							+ ConstantesClienteService.CLienteData.STATUSOKMENSAJE;
				}

			} else {

				return ConstantesClienteService.CLienteData.STATUSCODEOK
						+ ConstantesClienteService.CLienteData.STATUSOKMENSAJE;
			}

			throw new CustomRuntimeException(ConstantesClienteService.CLienteData.STATUSCODEERROR
					+ ConstantesClienteService.CLienteData.STATUSMENSAJEERROR);
		} catch (Exception e) {// Error del servidor
			logger.error(e.getMessage());
			throw new CustomRuntimeException(e.getMessage());
		}
	}

	/**
	 * El método consulta por documento o nombre si la persona se encuentra en lista
	 * de riesgo se llama un servicio externo para esta validación
	 * 
	 * @author OLM05871
	 * @throws JsonProcessingException 
	 */
	@Override
	public String getListConsulta(String issuedIdentValue, String nameValue) {

		logger.info("Ingreso al metodo getListConsulta()");

		ListConsultaDTO datosFinal = null;
		ListConsultaDTO errorFinal = null;
		// path del servicio
		String apiUrl = URLSERVICELISTA;

		// datos de consulta cliente
		String nombreCliente = nameValue;
		String custPermId = issuedIdentValue;

		// datos del usuario quien consulta
		String comentario = "Consulta desde Capa backend openshift para vinculacion";
		String nombreUSuario = "Onboarding";

		// datos auditoría aplicación
		String applicationCode = "0BD1";
		String employeeIdentlNum = "Onboarding";

		// Url para petición al servicio externo
		apiUrl += (nombreCliente == null) ? "" : "&SPName=" + nameValue;
		apiUrl += (custPermId == null) ? "" : "&custPermId=" + custPermId;
		apiUrl += (comentario == null) ? "" : "&comment=" + comentario;
		apiUrl += (applicationCode == null) ? "" : "&applicationCode=" + applicationCode;
		apiUrl += (employeeIdentlNum == null) ? "" : "&employeeIdentlNum=" + employeeIdentlNum;
		apiUrl += (nombreUSuario == null) ? "" : "&userName=" + nombreUSuario;

		// Cabecera para la petición al servicio externo
		String msgRqHdr = "";
		
		try {
			msgRqHdr = this.createMsgRqHdr();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept-Encoding", " gzip,deflate");
		headers.add("MsgRqHdr", msgRqHdr);
		headers.add("RqUID", rqUIDRequest);

		HttpEntity<String> customHeaders = new HttpEntity<String>("parameters", headers);

		try {

			String respEntity = apiRestClient.exchange(apiUrl, HttpMethod.GET, customHeaders, String.class).getBody();

			datosFinal = gson.fromJson(respEntity, ListConsultaDTO.class);

		} catch (RestClientException e) {
			logger.info("Error petición REST a " + apiUrl);

			if (e instanceof HttpStatusCodeException) {

				logger.error("Error codigo HTTP", e);
				HttpStatus status = ((HttpStatusCodeException) e).getStatusCode();

				if (status.value() == 500) {
					logger.info("Salio del metodo getListConsulta()");
					throw new CustomRuntimeException(ConstantesClienteService.CLienteList.STATUSCODEERROR500,
							ConstantesClienteService.CLienteList.STATUSMESAJEERROR500);
				} else {
					String errorResponse = ((HttpStatusCodeException) e).getResponseBodyAsString();
					errorFinal = gson.fromJson(errorResponse, ListConsultaDTO.class);

					logger.info("Salio del metodo getListConsulta()");

					throw new CustomRuntimeException(errorFinal.getStatus().getServerStatusCode().concat("_"),
							errorFinal.getStatus().getStatusDesc());
				}

			} else {
				logger.error("Otro tipo de error", e);
			}
		}

		if (datosFinal != null) {

			if (datosFinal.getQueryStatus().getStatusCode() == ConstantesClienteService.CLienteList.VALACEPTADO
					|| datosFinal.getQueryStatus()
							.getStatusCode() == ConstantesClienteService.CLienteList.VALAPROBADO) {

				logger.info("Salio del metodo getListConsulta()");
				return ConstantesClienteService.CLienteList.STATUSCODEOK
						+ ConstantesClienteService.CLienteList.STATUSOKMENSAJE;
			} else {
				logger.info("Salio del metodo getListConsulta()");

				throw new CustomRuntimeException(ConstantesClienteService.CLienteList.STATUSCODEERROR
						+ ConstantesClienteService.CLienteList.STATUSMENSAJEERROR);

			}
		} else {
			logger.info("Salio del metodo getListConsulta()");
			throw new CustomRuntimeException(ConstantesClienteService.CLienteList.STATUSCODEERROR + "Error servicio");
		}

	}

	/*
	 * @Descripction: Consulta el servicio consultarUsuario
	 * 
	 * @Autor: OJC04348
	 */
	@Override
	public String getConsultarUsuario(String NumeroDocumento) {
		ConsultarUsuarioDTO datosFinal = null;

		try {
			String url = URLSERVICECONSULTARUSUARIO;
			String apiUrl = url + "NumeroDocumento=" + NumeroDocumento;

			try {

				String respEntity = apiRestClient.exchange(apiUrl, HttpMethod.GET, null, String.class).getBody();
				datosFinal = gson.fromJson(respEntity, ConsultarUsuarioDTO.class);

				String genero = "";
				String fechaNacimiento = "";

				if (datosFinal.getDatos() != null) {
					genero = datosFinal.getDatos().getSexo().equals("M") ? "Masculino" : "Femenino";

	
					DateFormat format = new SimpleDateFormat("yyyyMMdd");
					Date date = format.parse(datosFinal.getDatos().getFechaNacimiento());
					fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").format(date);

					DocumentDTO documento = new DocumentDTO();
					documento.setBirthdate(fechaNacimiento);

					DocumentDTO[] document = { documento };

					responseOkData.setDocument(document);

				}
				responseOkData.setGender(genero);

			} catch (RestClientException e) {
				logger.info("Error petición REST a " + apiUrl);
				if (e.getRootCause() instanceof SocketTimeoutException) {
					logger.error("SocketTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteConsulta.STATUSCODEERROR + "Socket Time out");
				} else if (e.getRootCause() instanceof TimeoutException) {
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteConsulta.STATUSCODEERROR + "Time out");

				} else if (e.getRootCause() instanceof ConnectException) {
					logger.error("ConnectTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteConsulta.STATUSCODEERROR + "Conexion error");
				} else if (e instanceof HttpStatusCodeException) {
					logger.error("Error por codigos HTTP", e);
					HttpStatus status = ((HttpStatusCodeException) e).getStatusCode();
					if (status.value() == 500) {
						throw new CustomRuntimeException(ConstantesClienteService.CLienteConsulta.STATUSCODEERROR500,
								ConstantesClienteService.CLienteConsulta.STATUSMESAJEERROR500);
					}

				}
			}

//			if (datosFinal.getOperacionExitosa().equals(ConstantesClienteService.CLienteConsulta.OPERACIONVALEXITOSA)
//					&& datosFinal.getDatos() != null && datosFinal.getDatos().getEnroladoDactilar()
//							.equals(ConstantesClienteService.CLienteConsulta.ENROLADOVALEXITOSO)) {
//				return ConstantesClienteService.CLienteConsulta.STATUSCODEOK
//						+ ConstantesClienteService.CLienteConsulta.STATUSOKMENSAJE;
//			}

			return ConstantesClienteService.CLienteConsulta.STATUSCODEOK
					+ ConstantesClienteService.CLienteConsulta.STATUSOKMENSAJE;

		} catch (Exception e) {// Error del servidor
			logger.error(e.getMessage());
			throw new CustomRuntimeException(e.getMessage());
		}
	}

	/*
	 * @Descripction: Consulta el servicio cosultarUsuarioAni
	 * 
	 * @Autor: OJC04348
	 */
	@Override
	public String getConsultarClienteAni(String identificadorProceso, String numeroDocumento) {

		ConsultarClienteAniDTO datosFinal = null;
		Date fecha = new Date();
		String outNumProceso = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(fecha).concat(numeroDocumento);

		try {
			String url = URLSERVICEANY;
			String apiUrl = url + "IdentificadorProceso=" + outNumProceso + "&NumeroDocumento=" + numeroDocumento;
			String[] valOut = ConstantesClienteService.CLienteConsultaAni.ESTADOVALDOCUMENTO.split("_");

			try {

				String respEntity = apiRestClient.exchange(apiUrl, HttpMethod.GET, null, String.class).getBody();
				datosFinal = gson.fromJson(respEntity, ConsultarClienteAniDTO.class);

				responseOkData.setFirst_name(datosFinal.getDatos().getPrimerNombre());
				responseOkData.setSecond_name(datosFinal.getDatos().getSegundoNombre());
				responseOkData.setFirst_surname(datosFinal.getDatos().getPrimerApellido());
				responseOkData.setSecond_surname(datosFinal.getDatos().getSegundoApellido());

			} catch (RestClientException e) {
				logger.info("Error petición REST a " + apiUrl);
				if (e.getRootCause() instanceof SocketTimeoutException) {
					logger.error("SocketTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteConsultaAni.STATUSCODEERROR + "Time out");
				} else if (e.getRootCause() instanceof ConnectException) {
					logger.error("ConnectTimeoutException", e);
					throw new CustomRuntimeException(
							ConstantesClienteService.CLienteConsultaAni.STATUSCODEERROR + "Conexion error");
				} else if (e instanceof HttpStatusCodeException) {
					logger.error("Error por codigos HTTP", e);
					HttpStatus status = ((HttpStatusCodeException) e).getStatusCode();
					if (status.value() == 500) {
						throw new CustomRuntimeException(ConstantesClienteService.CLienteConsultaAni.STATUSCODEERROR500,
								ConstantesClienteService.CLienteConsultaAni.STATUSMESAJEERROR500);
					}
				}
			}

			System.out.println(" salida de es to " + datosFinal.getOperacionExitosa());
			if (datosFinal.getOperacionExitosa().equals(ConstantesClienteService.CLienteConsultaAni.OPERACIONVALEXITOSA)
					&& (datosFinal.getDatos().getEstadoCedula().equals(valOut[0])
							|| datosFinal.getDatos().getEstadoCedula().equals(valOut[1]))) {
				return ConstantesClienteService.CLienteConsultaAni.STATUSCODEOK
						+ ConstantesClienteService.CLienteConsultaAni.STATUSOKMENSAJE;
			}
			throw new CustomRuntimeException(ConstantesClienteService.CLienteConsultaAni.STATUSCODEERROR
					+ ConstantesClienteService.CLienteConsultaAni.STATUSMENSAJEERROR);
		} catch (Exception e) {// Error del servidor
			logger.error(e.getMessage());
			throw new CustomRuntimeException(e.getMessage());
		}
	}
	
	
	/**
	 * Método para crear el objeto msgRqHdr solicitado en las cabeceras para servicios externos
	 * @return objeto en string
	 * @throws JsonProcessingException
	 */
	private String createMsgRqHdr() throws JsonProcessingException {
		
		RequestOutHeadersDTO requestHeaderPrincipal = new RequestOutHeadersDTO();
		 
		RequestOutHeadersRequest headerRequest = new RequestOutHeadersRequest();
		
		RequestOutMessageHeader messageHeader = new RequestOutMessageHeader();
		
		RequestOutHeadersMessageKey messageKey = new RequestOutHeadersMessageKey();
		
		RequestOutHeadersMessageInfo messageInfo = new RequestOutHeadersMessageInfo();
		
		messageKey.setIntegrationId(requestInHeaders.getTransactionId());
		
		messageInfo.setDateTime(requestInHeaders.getTimesTamp());
		messageInfo.setSystemId("ITAU");
		messageInfo.setOriginatorName(requestInHeaders.getApplication());
		messageInfo.setOriginatorType(requestInHeaders.getChannelId());
		messageInfo.setTerminalId("127.0.0.1");
		
		messageHeader.setMessageInfo(messageInfo);
		messageHeader.setMessageKey(messageKey);
		
		headerRequest.setMessageHeader(messageHeader);
		
		requestHeaderPrincipal.setHeaderRequest(headerRequest);
		
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		String msgRqHdr = mapper.writeValueAsString(requestHeaderPrincipal);
	
		if(requestInHeaders.getChannelId() == null) {
			msgRqHdr = "{\"HeaderRequest\":{\"MessageHeader\":{\"MessageKey\":{\"integrationId\":\"910cf3c1-99d1-40e6-a8e2-8423b120A010\"},\"MessageInfo\":{\"dateTime\":\""+timeStamp+"\",\"systemId\":\"ITAU\",\"originatorName\":\"Portal PN\",\"originatorType\":\"131\",\"terminalId\":\"127.0.0.1\"}}}}";
		}
		
		
		return msgRqHdr; 
	}

}
