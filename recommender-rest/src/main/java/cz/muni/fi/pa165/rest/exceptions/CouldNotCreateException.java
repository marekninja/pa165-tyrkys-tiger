package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marek Petrovič
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unspecified error occurred.")
public class CouldNotCreateException extends RuntimeException{

    public CouldNotCreateException(String message) {
        super(message);
    }

    public CouldNotCreateException(Throwable cause) {
        super(cause);
    }

    public CouldNotCreateException( String message, Throwable cause) {
        super(message, cause);
    }
}
