package br.inatel.dragrace.exception;

public class NothingToDeleteException extends RuntimeException {

    public NothingToDeleteException() {
        super("The race is already reseted");
    }
}
