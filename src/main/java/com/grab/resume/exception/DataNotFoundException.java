package com.grab.resume.exception;

public class DataNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 2560909094747611662L;
	private String message;
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return message;
	}

}
