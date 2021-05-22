package cz.muni.fi.pa165.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Matej Turek
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Input parameter was null.")
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
