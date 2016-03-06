package com.tutorialspoint.exceptions;

public class NoBookAvailableException extends Exception {
	private String message;
	
	public NoBookAvailableException(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String toString() {
		return message;
	}
}
