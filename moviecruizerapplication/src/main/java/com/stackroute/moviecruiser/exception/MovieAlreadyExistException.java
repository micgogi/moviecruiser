package com.stackroute.moviecruiser.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistException extends Exception{

	
	private String message;

	
	
	public MovieAlreadyExistException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieAlreadyExistException [message=" + message + "]";
	}
	
	
}
