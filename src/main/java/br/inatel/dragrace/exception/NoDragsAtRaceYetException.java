package br.inatel.dragrace.exception;

public class NoDragsAtRaceYetException extends RuntimeException {

    public NoDragsAtRaceYetException() {
        super("There's no drags at the race yet");
    }
}
