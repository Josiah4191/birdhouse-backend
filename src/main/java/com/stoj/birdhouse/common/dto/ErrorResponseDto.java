package com.stoj.birdhouse.common.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {

    private String message;
    private int code;
    private String details;
    private LocalDateTime timestamp;

    public ErrorResponseDto(String message, int code, String details, LocalDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
