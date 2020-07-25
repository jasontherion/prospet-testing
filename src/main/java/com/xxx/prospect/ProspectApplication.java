package com.xxx.prospect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.xxx.prospect.request.dto.RequestInHeadersDTO;
import com.xxx.prospect.response.dto.ResponseErrorDTO;
import com.xxx.prospect.response.dto.ResponseOkDataDTO;

@SpringBootApplication
public class ProspectApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public RequestInHeadersDTO getRequestInHeaders() {
		return new RequestInHeadersDTO();
	}

	@Bean
	public ResponseErrorDTO getResponseDTO() {
		return new ResponseErrorDTO();
	}

	@Bean
	public ResponseOkDataDTO getResponseOkDataDTO() {

		return new ResponseOkDataDTO();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProspectApplication.class, args);
	}

}
