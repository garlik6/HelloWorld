package ch6.dop;


public class ClassLocationException extends RuntimeException {
    public ClassLocationException() {
        super();
    }

    public ClassLocationException(String message) {
        super(message);
    }

    public ClassLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassLocationException(Throwable cause) {
        super(cause);
    }
}
