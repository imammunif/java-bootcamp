package com.dansmultipro.ims.handler;

import com.dansmultipro.ims.dto.ErrorResponseDto;
import com.dansmultipro.ims.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto<List<String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        var errors = ex.getBindingResult().getAllErrors().stream()
                .map((ObjectError oe) -> oe.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex
    ){
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex
    ){
        var errors = "Invalid body request JSON format";
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleNoResourceFoundException(
            NoResourceFoundException ex
    ){
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex
    ) {
        var errors = "Unable to delete, referenced by other records";
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleNotFoundException(
            NotFoundException ex
    ) {
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleDataIntegrityViolationException(
            DataIntegrityException ex
    ) {
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataMissMatchException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleDataMissMatchException(
            DataMissMatchException ex
    ) {
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleInvalidQuantityException(
            InvalidQuantityException ex
    ) {
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleInvalidIdException(
            InvalidIdException ex
    ) {
        var errors = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

}
