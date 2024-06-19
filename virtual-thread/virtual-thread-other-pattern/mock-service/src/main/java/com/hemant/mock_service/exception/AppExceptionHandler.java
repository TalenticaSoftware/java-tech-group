package com.hemant.mock_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    protected ResponseEntity<Object> handleAppExceptions(NoSuchElementException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        HttpHeaders headers = this.getRequiredHeaders();
        return this.handleExceptionInternal(exception, new ErrorDetail(exception), headers, HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler({AppException.class})
    protected ResponseEntity<Object> handleAppExceptions(AppException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        HttpHeaders headers = this.getRequiredHeaders();
        return this.handleExceptionInternal(exception, new ErrorDetail(exception), headers, exception.getHttpStatus(),
                request);
    }


    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleBaseExceptions(Exception exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        HttpHeaders headers = this.getRequiredHeaders();
        return this.handleExceptionInternal(exception, new ErrorDetail(exception), headers,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetail(ex), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetail(ex), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetail(ex), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetail(ex), headers, status, request);
    }

    private HttpHeaders getRequiredHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
