package com.caroline.smc.exception;

public class NotExistException extends RuntimeException {
	private static final long serialVersionUID = 1459912743485787177L;

	public NotExistException() {
    }

    public NotExistException(String message) {
        super(message);
    }
}
