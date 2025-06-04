package org.example.common.handler;

import org.example.common.response.error.ClientErrorResponse;
import org.example.common.response.error.CustomException;
import org.example.common.response.error.ExceptionConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class ClientExceptionHandler {   // 4XX 에러 처리

    /**
     * 400 Error
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ClientErrorResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleExceptions(ex, ExceptionConfig.ILLEGAL_ARGUMENT);
    }

    /**
     * 400 Error
     * HttpMessageNotReadableException 클래스는 HttpMessageConversionException 클래스를 상속함
     */
    @ExceptionHandler({HttpMessageConversionException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ClientErrorResponse<?>> handleHttpMessageNotReadableException(HttpMessageConversionException ex) {
        return handleExceptions(ex, ExceptionConfig.HTTP_MESSAGE_NOT_READABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ClientErrorResponse<?>> handleBadRequestException(BadRequestException ex) {
        log.error("알 수 없는 에러 발생[400] : {}", String.valueOf(ex.getCause()));
        return handleExceptions(ex, ExceptionConfig.BAD_REQUEST);
    }

    /**
     * 실은, NoSuchElementException 의 경우 RuntimeException, 즉 5XX Exception 을 상속한다.
     * 그러나 일반적으로 API 응답으로 사용할 시에는 4XX 에러로 처리하는 것이 일반적이다.
     * Read Error Handling
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ClientErrorResponse<?>> handleNoSuchElementException(NoSuchElementException ex) {
        return handleExceptions(ex, ExceptionConfig.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ClientErrorResponse<?>> handleNullPointerException(NullPointerException ex) {
        return handleExceptions(ex, ExceptionConfig.NULL_POINTER);
    }

    /**
     * 405 Error - HTTP 메서드가 맞지 않음.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ClientErrorResponse<Map<String, Object>>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Set<HttpMethod> supportedMethods = new LinkedHashSet<>();
        if (ex.getSupportedHttpMethods() != null) {
            supportedMethods = ex.getSupportedHttpMethods();
        }
        Map<String, Object> errorDetails = Map.of("supportedMethods", supportedMethods.toString());
        return handleExceptions(ex, ExceptionConfig.METHOD_TYPE_ERROR, errorDetails);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ClientErrorResponse<?>> handleForeignKeyException(SQLIntegrityConstraintViolationException ex) {
        log.warn("주로 참조 무결성 제약조건에서 이슈가 있는 오류 발생 : {} ", ex.getMessage());
        return handleExceptions(ex, ExceptionConfig.FOREIGN_KEY_CONFLICT);
    }

    /**
     * ★ Custom API Exception
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ClientErrorResponse<Map<String, Object>>> handleCustomException(CustomException ex) {
        return handleExceptions(ex, ex.getConfig(), ex.getErrorDetails());
    }

    /**
     * 정형화된 ErrorResponse
     */
    private ResponseEntity<ClientErrorResponse<?>> handleExceptions(Exception ex, ExceptionConfig conf) {
        log.error("4XX 예외 발생: {} {}", conf.getHttpStatus(), ex.getMessage(), ex); // 중요한 오류 (서버 중단 가능성)
//        log.warn("주의: 예상치 못한 예외 발생 {}", ex.getMessage()); // 경고 수준 로그
//        log.info("예외가 발생했습니다. 사용자 요청 확인 필요."); // 운영자가 알아야 하는 일반 로그
//        log.debug("예외 발생 원인 분석 중: {}", ex, ex); // 상세 디버깅 정보 (개발 중 사용)

        return ResponseEntity.status(conf.getHttpStatus())
                .body(new ClientErrorResponse<>(conf));
    }

    /**
     * 에러의 이유를 자유롭게 담는 ErrorResponse
     */
    private ResponseEntity<ClientErrorResponse<Map<String, Object>>> handleExceptions(Exception ex,
                                                                                        ExceptionConfig conf,
                                                                                        Map<String, Object> errorDetails) {
        log.error("4XX 예외 발생: {} {}", conf.getHttpStatus(), ex.getMessage(), ex);
        return ResponseEntity.status(conf.getHttpStatus())
                .body(new ClientErrorResponse<>(conf, errorDetails));
    }
}
