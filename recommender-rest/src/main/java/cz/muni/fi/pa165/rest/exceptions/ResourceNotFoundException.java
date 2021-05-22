package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception converted by MyExceptionHandler to NOT_FOUND HTTP status.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resource was not found!")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException( String message, Throwable cause) {
        super(message, cause);
    }
}
