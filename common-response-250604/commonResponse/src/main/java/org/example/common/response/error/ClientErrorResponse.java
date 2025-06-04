package org.example.common.response.error;

import org.example.common.response.ApiResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientErrorResponse<T> extends ApiResponse<T> {

    public ClientErrorResponse(ExceptionConfig conf) {
        super(conf.getCode(), conf.getMessage());
    }

    public ClientErrorResponse(ExceptionConfig conf, T errorDetail) {
        super(conf.getCode(), conf.getMessage(), errorDetail);
    }
}
