package com.hemant.mock_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    AppErrorCodes code;
    List<FieldErrorDetail> detail;

    public AppException(AppErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes;
    }

    public AppException(AppErrorCodes errorCodes, List<FieldErrorDetail> detail) {
        super(errorCodes.getMessage());
        this.code = errorCodes;
        this.detail = detail;
    }

    public AppException(AppErrorCodes errorCodes, String message, List<FieldErrorDetail> detail) {
        super(message);
        this.code = errorCodes;
        this.detail = detail;
    }

    public AppException(AppErrorCodes code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(AppErrorCodes code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return code.getHttpStatus();
    }
}
