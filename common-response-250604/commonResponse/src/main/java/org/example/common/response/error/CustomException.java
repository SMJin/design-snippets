package org.example.common.response.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionConfig config;
    private final Map<String, Object> errorDetails;

    public CustomException(ExceptionConfig config) {
        super(config.getMessage());
        this.config = config;
        this.errorDetails = null;
    }

    public CustomException(ExceptionConfig config, Map<String, Object> errorDetails) {
        super(config.getMessage());
        this.config = config;
        this.errorDetails = errorDetails;
    }

    public void addDetail(String field, Object reason) {
        this.errorDetails.put(field, reason);
    }

    public HttpStatus getHttpStatus() {
        return config.getHttpStatus();
    }
}

