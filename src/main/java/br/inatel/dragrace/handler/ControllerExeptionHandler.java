package br.inatel.dragrace.handler;

import br.inatel.dragrace.exception.CarDataApiConnectionException;
import br.inatel.dragrace.exception.CarNotFoundException;
import br.inatel.dragrace.model.rest.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerExeptionHandler {

    @ExceptionHandler(CarDataApiConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error carDataApiConnectionException(CarDataApiConnectionException carDataApiConnectionException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(carDataApiConnectionException.getMessage())
                .build();
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error CarNotFoundException(CarNotFoundException carNotFoundException){
        return Error.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(carNotFoundException.getMessage())
                .build();
    }
}
