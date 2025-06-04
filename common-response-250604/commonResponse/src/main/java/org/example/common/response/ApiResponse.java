package org.example.common.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class ApiResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    protected ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    protected ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
