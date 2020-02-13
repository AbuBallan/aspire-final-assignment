package com.aspire.cart.exception;


import com.aspire.common.commandbus.exception.NoHandlerFoundForCommandException;
import com.aspire.common.exception.AbstractException;
import com.aspire.common.exception.ErrorInfo;
import com.aspire.common.exception.ExceptionConfigurationProperties;
import com.aspire.common.querybus.exception.NoHandlerFoundForQueryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DependenciesExceptionHandler {

    private final ExceptionConfigurationProperties exceptions;

    public DependenciesExceptionHandler(ExceptionConfigurationProperties exceptions) {
        this.exceptions = exceptions;
    }

    @ExceptionHandler({
            NoHandlerFoundForCommandException.class,
            NoHandlerFoundForQueryException.class,
    })
    public ResponseEntity<ErrorInfo> handleProjectExceptions(AbstractException e){
        String exceptionClassName = e.getClass().getSimpleName();
        ErrorInfo errorInfo = exceptions.getExceptions().get(exceptionClassName);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}
