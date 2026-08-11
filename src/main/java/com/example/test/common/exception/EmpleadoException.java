package com.example.test.common.exception;

public class EmpleadoException extends RuntimeException {

    private final String errorCode;

    public EmpleadoException(String message) {
        super(message);
        this.errorCode = "EMPLEADO_ERROR";
    }

    public EmpleadoException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
