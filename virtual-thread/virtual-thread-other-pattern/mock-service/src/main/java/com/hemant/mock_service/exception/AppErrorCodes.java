package com.hemant.mock_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorCodes {
    RECORD_NOT_FOUND("Record not found", HttpStatus.NOT_FOUND), USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    RECORD_ALREADY_EXISTS("Record Already Exists", HttpStatus.CONFLICT),
    INSUFFICIENT_PERMISSIONS("Access Denied", HttpStatus.FORBIDDEN),
    UNAUTHORIZED_ACCESS("Unauthorized Access", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("Something went wrong. Please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR),
    UNEXPECTED_ERROR("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_DOWN("external service is down, please try again after sometime", HttpStatus.BAD_GATEWAY),
    UNAUTHORIZED_TOKEN("Unauthorized Access", HttpStatus.UNAUTHORIZED),

    NOT_VALID_REQUEST("Not Valid Request", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS("Invalid credentials, please verify credentials", HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus httpStatus;

    public static AppErrorCodes appErrorCodeFromMessage(String message) {
        for (AppErrorCodes errorCode : AppErrorCodes.values()) {
            if (errorCode.getMessage().equals(message)) {
                return errorCode;
            }
        }
        return AppErrorCodes.INTERNAL_SERVER_ERROR;
    }

    public static AppErrorCodes appErrorCodeFromName(String name) {
        for (AppErrorCodes errorCode : AppErrorCodes.values()) {
            if (errorCode.name().equals(name)) {
                return errorCode;
            }
        }
        return null;
    }
}
