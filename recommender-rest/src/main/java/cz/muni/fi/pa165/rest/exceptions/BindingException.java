package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Matej Turek
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error occurred during binding")
public class BindingException extends RuntimeException {

    public BindingException(String message) {
        super(message);
    }

    public BindingException(Throwable cause) {
        super(cause);
    }

    public BindingException( String message, Throwable cause) {
        super(message, cause);
    }
}
