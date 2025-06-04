package org.example.common.util;

import org.example.common.response.ApiResponse;
import org.example.common.response.error.CustomException;
import org.example.common.response.error.ExceptionConfig;
import org.example.common.response.success.ResponseConfig;
import org.example.common.response.success.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Slf4j
public class ResponseUtil {

    public static ResponseEntity<ApiResponse<?>> authorized() {
        return success(ResponseConfig.OK);
    }

    public static ResponseEntity<ApiResponse<?>> acceptedAsync() {
        return success(ResponseConfig.VERIFIED);
    }

    public static <T> ResponseEntity<ApiResponse<?>> createSuccess(T data) {
        return success(ResponseConfig.CREATED, data);
    }

    public static <T> ResponseEntity<ApiResponse<?>> readSuccess(T data) {
        return success(ResponseConfig.READ, data);
    }

    public static <T> ResponseEntity<ApiResponse<?>> updateSuccess(T data) {
        return success(ResponseConfig.UPDATE, data);
    }

    public static <T> ResponseEntity<ApiResponse<?>> deleteSuccess(T data) {
        return success(ResponseConfig.DELETE, data);
    }

    public static <T> ResponseEntity<ApiResponse<?>> verifySuccess(T data) {
        return success(ResponseConfig.VERIFIED, data);
    }

    /**
     * 가장 경량화된 successResponse
     */
    public static ResponseEntity<ApiResponse<?>> success(ResponseConfig conf) {
        return success(conf, null);
    }

    /**
     * data 를 삽입가능한 successResponse
     */
    public static <T> ResponseEntity<ApiResponse<?>> success(ResponseConfig conf, T data) {
        return ResponseEntity.status(conf.getHttpStatus())
                .body(new SuccessResponse<>(conf, data));
    }

    /*
        인증 오류 - 보통 인증 쿠키나 세션 자체가 존재하지 않는 경우 401
     */
    public static ResponseEntity<ApiResponse<?>> unauthorized() {
        return error(ExceptionConfig.UNAUTHORIZED);
    }

    /*
        인가 오류 - 보통 사용자는 인증되었는데, 권한을 벗어나는 경우 403
     */
    public static ResponseEntity<ApiResponse<?>> forbidden() {
        return error(ExceptionConfig.FORBIDDEN);
    }

    public static ResponseEntity<ApiResponse<?>> unexpectedSeverError() {
        return error(ExceptionConfig.CHECKED_EXCEPTION);
    }

    public static ResponseEntity<ApiResponse<?>> error(ExceptionConfig conf) {
        log.warn("ResponseUtil : custom error 발생! -> conf={}", conf);
        throw new CustomException(conf);
    }

    public static ResponseEntity<ApiResponse<?>> error(ExceptionConfig conf,
                                                                                 Map<String, Object> errorDetails) {
        log.warn("ResponseUtil : custom error 발생! -> conf={} errorDetails={}", conf, errorDetails);
        throw new CustomException(conf, errorDetails);
    }
}
