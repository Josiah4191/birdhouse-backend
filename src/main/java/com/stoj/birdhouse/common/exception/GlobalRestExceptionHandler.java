package com.stoj.birdhouse.common.exception;

import com.stoj.birdhouse.common.dto.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAll(Exception ex) {
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String details = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), code, details, timestamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex) {
        int code = HttpStatus.NOT_FOUND.value();
        String details = HttpStatus.NOT_FOUND.getReasonPhrase();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), code, details, timestamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalState(IllegalStateException ex) {
        int code = HttpStatus.CONFLICT.value();
        String details = HttpStatus.CONFLICT.getReasonPhrase();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), code, details, timestamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }

    @ExceptionHandler(DuplicateOrderLineException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateOrderLine(DuplicateOrderLineException ex) {
        int code = HttpStatus.CONFLICT.value();
        String details = HttpStatus.CONFLICT.getReasonPhrase();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), code, details, timestamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        int code = HttpStatus.CONFLICT.value();
        String details = HttpStatus.CONFLICT.getReasonPhrase();
        LocalDateTime timestamp = LocalDateTime.now();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMessage(), code, details, timestamp);

        return ResponseEntity.status(code).body(errorResponseDto);
    }


}
