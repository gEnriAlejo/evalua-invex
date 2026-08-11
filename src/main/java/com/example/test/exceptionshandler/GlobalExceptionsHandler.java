package com.example.test.exceptionshandler;

import com.example.test.common.exception.EmpleadoException;
import com.example.test.records.ErrorResponseR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionsHandler {

    // Captura el error en versiones nuevas cuando se validan listas o parámetros directos
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<List<String>> handleHandlerMethodValidation(HandlerMethodValidationException ex) {

        log.info("mensaje");

        // Extrae todos los mensajes de error resolubles de los parámetros del método
        List<String> errorMessages = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ErrorResponseR> handleAbcException(EmpleadoException ex) {

        // Puedes definir el HttpStatus que mejor se adapte (ej. BAD_REQUEST, NOT_FOUND, etc.)
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponseR errorResponse = new ErrorResponseR(
                LocalDateTime.now(),
                status.value(),
                ex.getErrorCode(),
                ex.getMessage()
        );

        return ResponseEntity.status(status).body(errorResponse);
    }

    // Método genérico para atrapar CUALQUIER excepción
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        e.printStackTrace();

        // Retorna un mensaje al cliente con el error real que ocurrió
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno o de validación no capturado: " + e.getClass().getName() + " - " + e.getMessage());
    }
}
