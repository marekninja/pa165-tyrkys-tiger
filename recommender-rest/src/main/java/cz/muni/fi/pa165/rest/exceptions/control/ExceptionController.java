package cz.muni.fi.pa165.rest.exceptions.control;

import cz.muni.fi.pa165.rest.controllers.UserController;
import cz.muni.fi.pa165.rest.exceptions.*;
import cz.muni.fi.pa165.service.exceptions.AuthenticationException;
import cz.muni.fi.pa165.service.exceptions.NotExistException;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * @author Matej Turek
 */
@ControllerAdvice
public class ExceptionController {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<?> handleException(Exception exception) {

        HttpStatus status;

        if (exception instanceof BindingException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (exception instanceof CouldNotCreateException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (exception instanceof CouldNotUpdateException) {
            status = HttpStatus.NOT_ACCEPTABLE;
        } else if (exception instanceof ResourceAlreadyExistsException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (exception instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof AuthenticationException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof NotExistException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof NullArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorRepresentation errorRepresentation = new ErrorRepresentation(status.name(), exception.getMessage(), System.currentTimeMillis(), status.value());

        logger.debug("{}: {} - {}", exception.getMessage(), status, errorRepresentation.getMessage());

        return new ResponseEntity<>(errorRepresentation, status);
    }

}
