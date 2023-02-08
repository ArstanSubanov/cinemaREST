package com.arstansubanov.cinematica.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> cinemaNotFound(CinemaNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Cinema with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> hallNotFound(HallNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Hall with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> movieNotFound(MovieNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Movie with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> orderNotFound(OrderNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Order with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> priceNotFound(PriceNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Price with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> priceTypeNotFound(PriceTypeNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "PriceType with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> seatNotFound(SeatNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Seat with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> statusNotFound(StatusNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Status with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userNotFound(MovieSessionNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "MovieSession with this id not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> duplicateKeyException(DuplicateKeyException e){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Cannot save entity with this keys"), HttpStatus.NOT_FOUND);
    }
}
