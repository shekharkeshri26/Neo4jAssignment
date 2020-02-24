package com.api.assignment.exception;

public class PersonNotFoundException extends RuntimeException{

	public PersonNotFoundException(Long id) {
		super("Person id not found: " +id);
	}
	
}
