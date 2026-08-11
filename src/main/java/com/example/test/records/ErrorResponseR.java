package com.example.test.records;

import java.time.LocalDateTime;

public record ErrorResponseR(
        LocalDateTime timestamp,
        int status,
        String errorCode,
        String message) {
}
