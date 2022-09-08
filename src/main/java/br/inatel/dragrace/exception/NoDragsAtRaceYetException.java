package br.inatel.dragrace.exception;

public class NoDragsAtRaceYetException extends RuntimeException {

    public NoDragsAtRaceYetException() {
        super("there's no drags at the race yet");
    }
}
