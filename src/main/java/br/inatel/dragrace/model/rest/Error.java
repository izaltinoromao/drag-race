package br.inatel.dragrace.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author izaltino.
 * @since 09/09/2022
 */
@Data
@AllArgsConstructor
@Builder
public class Error {

    private HttpStatus httpStatusCode;
    private String message;
}
