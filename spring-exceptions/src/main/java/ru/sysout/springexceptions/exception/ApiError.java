package ru.sysout.springexceptions.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiError {
	
	private HttpStatus status;
	private String message;
	
	private String debugMessage;
	
	private List<FieldValidationError> fieldValidationErrors;

	ApiError() {
	}

	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.setDebugMessage(ex.getLocalizedMessage());
	}

	ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.setDebugMessage(ex.getLocalizedMessage());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(error -> {
			FieldValidationError subError = new FieldValidationError();
			subError.setField(error.getField());
			subError.setMessage(error.getDefaultMessage());
			subError.setRejectedValue(error.getRejectedValue());
			subError.setObject(error.getObjectName());
			this.addSubError(subError);
		});
	}

	private void addSubError(FieldValidationError subError) {
		if (fieldValidationErrors == null) {
			fieldValidationErrors = new ArrayList<>();
		}
		fieldValidationErrors.add(subError);
	}

	public List<FieldValidationError> getFieledValidationErrors() {
		return fieldValidationErrors;
	}

	public void setFieldValidationErrors(List<FieldValidationError> apiValidationErrors) {
		this.fieldValidationErrors = apiValidationErrors;
	}
}
