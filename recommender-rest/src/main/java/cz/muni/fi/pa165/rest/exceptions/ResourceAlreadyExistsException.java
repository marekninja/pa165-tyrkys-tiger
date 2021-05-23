package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Matej Turek
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Resource already exists.")
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ResourceAlreadyExistsException( String message, Throwable cause) {
        super(message, cause);
    }
}
