package org.taljaard.training.trnmicroservbeerservice.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolations(ConstraintViolationException cve) {
        return new ResponseEntity<List<String>>(
                cve.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList()),
                BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ObjectError>> handleBindException(BindException be) {
        return new ResponseEntity<List<ObjectError>>(be.getAllErrors(), BAD_REQUEST);
    }
}
