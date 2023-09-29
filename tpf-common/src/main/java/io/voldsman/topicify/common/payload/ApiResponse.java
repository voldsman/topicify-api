package io.voldsman.topicify.common.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private String message;

    private boolean success;

    private T data;

    private LocalDateTime timestamp;

    public ApiResponse(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
