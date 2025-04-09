package com.devsu.bank.infraestructure.exception.feign;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsu.bank.domain.model.ErrorResponse;
import com.devsu.bank.infraestructure.exception.LocalNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignErrorConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ClientErrorDecoder();
    }

    public static class ClientErrorDecoder implements ErrorDecoder {

        private final ErrorDecoder defaultDecoder = new Default();

        @Override
        public Exception decode(String methodKey, Response response) {
        	
            try (InputStream body = response.body().asInputStream()) {
            	System.out.println(body);
                ObjectMapper mapper = new ObjectMapper();
               
                ErrorResponse error = mapper.readValue(body, ErrorResponse.class);

                if (response.status() == 404) {
                    return new LocalNotFoundException(error.getMessage());
                }

                return new RuntimeException("Error en servicio remoto: " + error.getMessage());

            } catch (IOException e) {
                return defaultDecoder.decode(methodKey, response);
            }
        }
    }
}