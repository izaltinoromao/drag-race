package br.inatel.dragrace.exception;

/**
 * Exception class when you try to reset a drag or set winners and there's no drags yet
 * @author izaltino.
 * @since 09/09/2022
 */
public class NoDragsAtRaceYetException extends RuntimeException {

    public NoDragsAtRaceYetException() {
        super("There's no drags at the race yet");
    }
}
