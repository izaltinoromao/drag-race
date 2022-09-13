package br.inatel.dragrace.handler;

import br.inatel.dragrace.exception.*;
import br.inatel.dragrace.model.rest.Error;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClientException;

/**
 * Class to handle all the exceptions and give a message to the client
 * @author izaltino.
 * @since 09/09/2022
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(WebClientException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error carDataApiConnectionException(CarDataApiConnectionException carDataApiConnectionException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(carDataApiConnectionException.getMessage())
                .build();
    }

    @ExceptionHandler(JDBCConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error DataBaseConnectionException(JDBCConnectionException jdbcConnectionException){
        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(jdbcConnectionException.getMessage())
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

    @ExceptionHandler(DragAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Error DragAlreadyExistsException(DragAlreadyExistsException dragAlreadyExistsException){
        return Error.builder()
                .httpStatusCode(HttpStatus.CONFLICT)
                .message(dragAlreadyExistsException.getMessage())
                .build();
    }

    @ExceptionHandler(NothingToDeleteException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error NothingToDeleteException(NothingToDeleteException nothingToDeleteException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(nothingToDeleteException.getMessage())
                .build();
    }

    @ExceptionHandler(NoDriverFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error NoDriverFoundException(NoDriverFoundException noDriverFoundException){
        return Error.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(noDriverFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(NoDragsAtRaceYetException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error NoDragsAtRaceYetException(NoDragsAtRaceYetException noDragsAtRaceYetException){
        return Error.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(noDragsAtRaceYetException.getMessage())
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(String.valueOf(methodArgumentNotValidException.getFieldError().getField() +  " must not be empty"))
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error missingServletRequestParameterException(MissingServletRequestParameterException missingServletRequestParameterException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(String.valueOf(missingServletRequestParameterException.getParameterName() + " parameter is required")).build();
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error propertyReferenceException(PropertyReferenceException propertyReferenceException){
        return Error.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(propertyReferenceException.getMessage())
                .build();
    }
}
