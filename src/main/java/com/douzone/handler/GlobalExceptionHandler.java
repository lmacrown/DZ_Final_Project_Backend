package com.douzone.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.douzone.entity.ExceptionResponseVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponseVO> handleNullPointerException(NullPointerException e) {
        return buildExceptionResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, "NullPointerException occurred.", e);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponseVO> handleSQLException(SQLException e) {
        return buildExceptionResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, "SQLException occurred.", e);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionResponseVO> handleIOException(IOException e) {
        return buildExceptionResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, "IOException occurred.", e);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ExceptionResponseVO> handleMissingPathVariableException(MissingPathVariableException e) {
        return buildExceptionResponseVO(HttpStatus.BAD_REQUEST, "Missing path variable.", e);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ExceptionResponseVO> handleTypeMismatchException(TypeMismatchException e) {
        return buildExceptionResponseVO(HttpStatus.BAD_REQUEST, "Type mismatch.", e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponseVO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return buildExceptionResponseVO(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not supported.", e);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponseVO> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return buildExceptionResponseVO(HttpStatus.BAD_REQUEST, "Missing request parameter.", e);
    }

    // 나머지 예외들을 처리하기 위한 메서드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseVO> handleAllExceptions(Exception e) {
        return buildExceptionResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.", e);
    }

    // 공통된 로직을 처리하는 메서드
    private ResponseEntity<ExceptionResponseVO> buildExceptionResponseVO(HttpStatus status, String message, Exception e) {
        ExceptionResponseVO errorResponse = new ExceptionResponseVO(false, LocalDateTime.now(), status.value(), message, e.getMessage());
        log.error("Server Error: Status - {}, Message - {}, Details - {}", status.value(), message, e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}

