package com.example.food.exception;

public class FailToUploadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FailToUploadException(String message) {
		super(message);
	}
}
