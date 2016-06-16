package com.it.exception;

/**
 * Created by xieyue on 2016/6/8.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException() {}

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }
}
