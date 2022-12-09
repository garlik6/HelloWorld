package ch6.n14;

public class CloseFailureException extends Exception {
    public CloseFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
