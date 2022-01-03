package com.daniel.unisystest.exceptions.business;

public class JobNameAlreadyExistsExpception extends RuntimeException {

	private static final long serialVersionUID = -7160483688519662955L;

	public JobNameAlreadyExistsExpception(String message) {
		super(message);
	}

}
