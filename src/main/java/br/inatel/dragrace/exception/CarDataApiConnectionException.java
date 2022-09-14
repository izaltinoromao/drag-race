package br.inatel.dragrace.exception;

import org.springframework.web.reactive.function.client.WebClientException;

/**
 * Exception class to a CarDataAPI fail
 * @author izaltino.
 * @since 09/09/2022
 */
public class CarDataApiConnectionException extends RuntimeException{

    /**
     * @param webClientException
     */
    public CarDataApiConnectionException(WebClientException webClientException){
        super("Connection with the external API failed" + webClientException.getMessage());
    }
}
