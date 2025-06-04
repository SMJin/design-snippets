package org.example.common.response.error;

import org.example.common.response.ApiResponse;
import lombok.Getter;

@Getter
public class ServerErrorResponse<T> extends ApiResponse<T> {

    public ServerErrorResponse(ExceptionConfig conf) {
        super(conf.getCode(), conf.getMessage());
    }
}
