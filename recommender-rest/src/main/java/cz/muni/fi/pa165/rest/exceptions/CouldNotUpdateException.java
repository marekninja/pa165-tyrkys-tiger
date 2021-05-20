package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Matej Turek
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Error occurred during update")
public class CouldNotUpdateException extends RuntimeException {

    public CouldNotUpdateException(String message) {
        super(message);
    }

    public CouldNotUpdateException(Throwable cause) {
        super(cause);
    }

    public CouldNotUpdateException( String message, Throwable cause) {
        super(message, cause);
    }
}
