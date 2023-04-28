package com.restfulProject.restfulProject.exception;

import com.restfulProject.restfulProject.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class CustomResponseEntity extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllException(Exception e, WebRequest request){
        ErrorMessage errorMsg = new ErrorMessage(LocalDateTime.now(),e.getMessage(), request.getDescription(false));

        return new ResponseEntity(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception e, WebRequest request){
        ErrorMessage errorMsg = new ErrorMessage(LocalDateTime.now(),e.getMessage(), request.getDescription(false));

        return new ResponseEntity(errorMsg, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorMessage errorMsg = new ErrorMessage(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST);
    }

}
