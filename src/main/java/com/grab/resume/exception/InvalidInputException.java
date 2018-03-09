package com.grab.resume.exception;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = -7596580690102352584L;
	private String message;

	public InvalidInputException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
