package br.inatel.dragrace.exception;

/**
 * Exception class when the race is already reseted, and you try to reset it again
 * @author izaltino.
 * @since 09/09/2022
 */
public class NothingToDeleteException extends RuntimeException {

    public NothingToDeleteException() {
        super("The race is already reseted");
    }
}
