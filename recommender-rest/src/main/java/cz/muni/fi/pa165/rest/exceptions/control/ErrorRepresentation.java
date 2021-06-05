package cz.muni.fi.pa165.rest.exceptions.control;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Matej Turek
 */
@Getter
@Setter
public class ErrorRepresentation {

    private String name;
    private String message;

    public ErrorRepresentation() {}

    public ErrorRepresentation(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
