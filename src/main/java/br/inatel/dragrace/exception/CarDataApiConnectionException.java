package br.inatel.dragrace.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class CarDataApiConnectionException extends RuntimeException{

    public CarDataApiConnectionException(WebClientException webClientException){
        super("Connection with the external API failed" + webClientException.getMessage());
    }
}
