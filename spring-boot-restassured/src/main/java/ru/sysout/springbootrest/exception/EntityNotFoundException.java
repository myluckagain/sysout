package ru.sysout.springbootrest.exception;

public class EntityNotFoundException extends Exception {

	public EntityNotFoundException(String id) {
		super("entity not found" + id);
	}

}
