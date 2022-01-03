package com.daniel.unisystest.exceptions.business;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2663625291538867648L;
	
	public TaskNotFoundException(String message) {
        super(message);
    }

}
