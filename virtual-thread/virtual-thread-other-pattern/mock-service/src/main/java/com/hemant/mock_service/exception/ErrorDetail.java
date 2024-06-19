package com.hemant.mock_service.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ErrorDetail {

    private String code;
    private String message;
    private List<FieldErrorDetail> detail;

    public ErrorDetail(AppException e) {
        this.code = e.getCode() == null ? "" : e.getCode().name();
        this.message = e.getMessage();
        this.detail = e.getDetail() == null ? null : e.getDetail();
    }

    ErrorDetail(MissingPathVariableException e) {
        this.code = "MISSING_PATH_VARIABLE";
        this.message = "Missing mandatory path variable : " + e.getVariableName();
    }

    ErrorDetail(MissingServletRequestParameterException e) {
        this.code = "MISSING_REQUEST_PARAMETER_VARIABLE";
        this.message = "Missing mandatory request parameter variable : " + e.getParameterName();
    }

    ErrorDetail(NoSuchElementException e) {
        this.code = "RECORD_NOT_FOUND";
        this.message = "Record not found";
    }


    public ErrorDetail(MethodArgumentNotValidException e) {
        List<FieldErrorDetail> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldErrorDetail(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        this.message = fieldErrors.get(0).getMessage();
        this.code = "METHOD_ARGUMENT_NOT_VALID";
        this.detail = fieldErrors;
    }

    public ErrorDetail(WebExchangeBindException e) {
        List<FieldErrorDetail> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldErrorDetail(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        this.message = fieldErrors.get(0).getMessage();
        this.code = "METHOD_ARGUMENT_NOT_VALID";
        this.detail = fieldErrors;
    }

    public ErrorDetail(MethodNotAllowedException e) {
        this.message = e.getBody().getDetail();
        this.code = "METHOD_NOT_ALLOWED";
    }

    public ErrorDetail(Exception e) {
        this.code = "UNEXPECTED_ERROR";
        this.message = "Unexpected error";
    }


    public ErrorDetail(MaxUploadSizeExceededException e) {
        this.code = "MAX_UPLOAD_SIZE_VIOLATION";
        this.message = e.getMessage();
    }

    ErrorDetail(HttpMessageNotReadableException e) {
        this.code = "UNEXPECTED_ERROR";
        this.message = e.getMessage();
    }

    public ErrorDetail(String code, String message) {
        this.code = code;
        this.message = message;
    }
}