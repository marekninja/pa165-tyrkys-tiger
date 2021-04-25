package cz.muni.fi.pa165.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * @author Matej Turek
 */
public class DataAccessExceptionImpl extends DataAccessException {

    public DataAccessExceptionImpl(String msg) {
        super(msg);
    }

    public DataAccessExceptionImpl(String msg, Throwable cause) {
        super(msg, cause);
    }
}