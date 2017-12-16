package org.logtrace.exception;

public class NumberRangeException extends Exception {

    public NumberRangeException() {
        super();
    }

    public NumberRangeException(String message) {
        super(message);
    }

    public NumberRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
