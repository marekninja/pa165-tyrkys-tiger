package cz.muni.fi.pa165.rest.exceptions;

/**
 * @author Marek Petrovič
 */
public class CouldNotCreateException extends RuntimeException{
    public CouldNotCreateException(String message) {
        super(message);
    }
}
