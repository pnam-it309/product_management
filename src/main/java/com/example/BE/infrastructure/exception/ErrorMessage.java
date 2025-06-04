package com.example.BE.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.description = description;
    }
}
