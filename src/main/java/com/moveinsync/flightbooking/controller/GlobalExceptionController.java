package com.moveinsync.flightbooking.controller;

import com.moveinsync.flightbooking.exceptions.FlightAuthException;
import com.moveinsync.flightbooking.exceptions.ExceptionResponse;
import com.moveinsync.flightbooking.exceptions.InvalidFlightException;
import com.moveinsync.flightbooking.exceptions.UserFlightException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = {InvalidFlightException.class})
    public ResponseEntity<ExceptionResponse> handleCustomException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(request);
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setError(ex.getClass().getSimpleName());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserFlightException.class})
    public ResponseEntity<ExceptionResponse> handleUserFlightException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(request);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setError(ex.getClass().getSimpleName());
        response.setMessage("Something went wrong");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {FlightAuthException.class})
    public ResponseEntity<ExceptionResponse> handleAuthException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(request);
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setError(ex.getClass().getSimpleName());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
