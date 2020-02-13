package com.aspire.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    private final ExceptionConfigurationProperties exceptions;

    public DefaultExceptionHandler(ExceptionConfigurationProperties exceptions) {
        this.exceptions = exceptions;
    }

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorInfo> handleProjectExceptions(AbstractException e){
        String exceptionClassName = e.getClass().getSimpleName();
        ErrorInfo errorInfo = exceptions.getExceptions().get(exceptionClassName);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
