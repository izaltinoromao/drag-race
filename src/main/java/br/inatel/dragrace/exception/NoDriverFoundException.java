package br.inatel.dragrace.exception;

/**
 * Exception class when you search a drag by driver but there's no drag with this driver
 * @author izaltino.
 * @since 09/09/2022
 */
public class NoDriverFoundException extends RuntimeException {

    /**
     * @param driver
     */
    public NoDriverFoundException(String driver) {
        super("No driver " + driver + " was found at Data Base");
    }
}
