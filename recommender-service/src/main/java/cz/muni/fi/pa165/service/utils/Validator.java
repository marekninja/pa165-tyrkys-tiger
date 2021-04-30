package cz.muni.fi.pa165.service.utils;

import cz.muni.fi.pa165.service.exceptions.NullArgumentException;

/**
 * @author Matej Turek
 */
public class Validator {

    public static void validate(Class<?> clazz, Object object, String message) throws NullArgumentException {
        if (object == null) {
            throw new NullArgumentException(clazz, message);
        }
    }
}
