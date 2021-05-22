package cz.muni.fi.pa165.service.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * @author Marek Petrovič
 */
public class NotExistException extends DataAccessException {
    public NotExistException(String msg) {
        super(msg);
    }
}
