package com.daniel.unisystest.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daniel.unisystest.exceptions.business.JobNameAlreadyExistsExpception;
import com.daniel.unisystest.exceptions.business.JobNotFoundException;
import com.daniel.unisystest.exceptions.business.TaskNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<CustomizedValidationError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<CustomizedValidationError> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			CustomizedValidationError erro = new CustomizedValidationError(error.getDefaultMessage(),
					((FieldError) error).getField());
			errors.add(erro);
		});
		return errors;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { JobNotFoundException.class, JobNameAlreadyExistsExpception.class, TaskNotFoundException.class })
	public CustomizedValidationError handleResourcesNotFoundExceptions(Exception ex) {
		return new CustomizedValidationError(ex.getMessage());
	}

}
