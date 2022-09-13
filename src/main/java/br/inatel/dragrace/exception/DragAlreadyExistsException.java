package br.inatel.dragrace.exception;

/**
 * Exception class when a drag already exist at the database
 * @author izaltino.
 * @since 09/09/2022
 */
public class DragAlreadyExistsException extends RuntimeException {

    /**
     * @param driver
     */
    public DragAlreadyExistsException(String driver) {
        super("The driver " + driver + " already have one drag");
    }
}
