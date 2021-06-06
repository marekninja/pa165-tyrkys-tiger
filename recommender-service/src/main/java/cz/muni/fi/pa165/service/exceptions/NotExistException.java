package cz.muni.fi.pa165.service.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marek Petrovič
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The resource was not found!")
public class NotExistException extends DataAccessException {
    public NotExistException(String msg) {
        super(msg);
    }
}
