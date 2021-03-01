package ru.sysout.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

//@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler({MyEntityNotFoundException.class, EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(RuntimeException ex, WebRequest request) {
      ApiError apiError = new ApiError("entity not found ex", ex.getMessage());
      return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}