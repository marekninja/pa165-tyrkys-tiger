package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Matej Turek
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Authentication credentials invalid!")
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException( String message, Throwable cause) {
        super(message, cause);
    }
}
