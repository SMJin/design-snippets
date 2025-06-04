package org.example.common.response.success;

import org.example.common.response.ApiResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SuccessResponse<T> extends ApiResponse<T> {

    public SuccessResponse(ResponseConfig conf) {
        super(conf.getCode(), conf.getMessage());
    }

    public SuccessResponse(ResponseConfig conf, T data) {
        super(conf.getCode(), conf.getMessage(), data);
    }
}
