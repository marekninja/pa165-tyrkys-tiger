package cz.muni.fi.pa165.service.exceptions;

/**
 * @author Matej Turek
 */
public class NullArgumentException extends IllegalArgumentException {
    public NullArgumentException(Class<?> clazz, String message) {
        super(String.format("{%s - %s}", clazz.getSimpleName(), message));
    }

    public NullArgumentException(Throwable cause) {
        super(cause);
    }

    public NullArgumentException(Class<?> clazz, String message, Throwable cause) {
        super(String.format("{%s - %s}", clazz.getSimpleName(), message), cause);
    }
}
