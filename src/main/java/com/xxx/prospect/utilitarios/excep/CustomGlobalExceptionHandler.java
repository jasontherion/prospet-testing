package com.xxx.prospect.utilitarios.excep;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;
import com.xxx.prospect.response.dto.ResponseErrorDTO;

/**
 * Esta clase se encarga de centralizar los handler según los errores que puedan
 * presentarse
 * 
 * @author OLM05871
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@InitBinder
	private void initBinder(WebDataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	
	
	/**
	 * Este método se encarga de controlar los errores que ocurran por el medio
	 * de @Valid
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Lista todos los errores

		List<Map<String, String>> errores = ex.getBindingResult().getFieldErrors().stream().map(x -> {
			Map<String, String> error = new LinkedHashMap<>();
			error.put(x.getField(), x.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());

		body.put("errores", errores);

		return new ResponseEntity<>(body, headers, status);
	}

	/**
	 * Este método se encarga de controlar los errores que ocurran en los request de
	 * las peticiones del micro servicio
	 */
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest resquest) {

		Map<String, Object> body = new LinkedHashMap<>();
		Gson gson = new Gson(); // Salida json

		ResponseErrorDTO responseError = new ResponseErrorDTO();
		String code = Integer.toString(status.value());
		
		responseError.setCode(code);
		responseError.setMessage("Falta algun parámetro en la cabecera");
		
		body.put("codigo", status.value());
		body.put("mensagem", gson.toJson(responseError));

		return new ResponseEntity<>(body, headers, status);
	}
}
