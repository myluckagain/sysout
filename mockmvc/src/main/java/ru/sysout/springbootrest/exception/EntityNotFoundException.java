package ru.sysout.springbootrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

	public EntityNotFoundException(String id) {
		super("entity not found: " + id);
	}

}
