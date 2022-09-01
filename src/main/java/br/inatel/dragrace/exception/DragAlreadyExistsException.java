package br.inatel.dragrace.exception;

public class DragAlreadyExistsException extends RuntimeException {

    public DragAlreadyExistsException(String driver) {
        super("The driver " + driver + " already have one drag");
    }
}
