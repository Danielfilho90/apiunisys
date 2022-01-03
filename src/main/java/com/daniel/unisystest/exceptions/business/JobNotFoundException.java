package com.daniel.unisystest.exceptions.business;

public class JobNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -4232725685445984849L;

	public JobNotFoundException(String message) {
        super(message);
    }

}