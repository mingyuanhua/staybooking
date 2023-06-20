package com.hmy.staybooking.controller;

import com.hmy.staybooking.exception.StayNotExistException;
import com.hmy.staybooking.exception.UserAlreadyExistException;
import com.hmy.staybooking.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// 面向切面程序设计 Aspect-oriented programming AOP
// controller advice to make Spring use CustomExceptionHandler when there's any exceptions in controller code
@ControllerAdvice
public class CustomExceptionHandler {

    // ExceptionHandler matches each exception to the corresponding handler function
    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<String> handleUserAlreadyExistExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Add a new ExceptionHandler for UserNotExistExceptions
    @ExceptionHandler(UserNotExistException.class)
    public final ResponseEntity<String> handleUserNotExistExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(StayNotExistException.class)
    public final ResponseEntity<String> handleStayNotExistExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
