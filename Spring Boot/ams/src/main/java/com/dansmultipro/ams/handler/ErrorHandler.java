package com.dansmultipro.ams.handler;

import com.dansmultipro.ams.dto.ErrorResponseDto;
import com.dansmultipro.ams.exception.DataIntegrityException;
import com.dansmultipro.ams.exception.DataMissMatchException;
import com.dansmultipro.ams.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
