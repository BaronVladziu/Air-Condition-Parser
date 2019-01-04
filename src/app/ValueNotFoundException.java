package app;

/**
 * Exception thrown when no requested value is found.
 * @author Bartłomiej Kuśmirek
 */
public class ValueNotFoundException extends Exception {

    /**
     * Constructor with message.
     * @param message description of exception
     */
    public ValueNotFoundException(String message) {
        super(message);
    }

}
