package com.caroline.smc.exception;

public class AlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1459912743485787177L;

	public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
