package br.inatel.dragrace.exception;

public class NoDriverFoundException extends RuntimeException {

    public NoDriverFoundException(String driver) {
        super("No driver " + driver + " was found at Data Base");
    }
}
