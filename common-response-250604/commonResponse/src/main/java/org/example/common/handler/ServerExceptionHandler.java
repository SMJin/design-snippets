package org.example.common.handler;

import org.example.common.response.error.ExceptionConfig;
import org.example.common.response.error.ServerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {   // 5XX 에러처리

    /*
     * Basic Error (Checked Exception) - Compile Error
     * ex. IOExcepton, SQLException, InterrupedException, ParseException 등
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerErrorResponse<?>> handleAllExceptions(Exception ex) {
        return handleExceptions(ex, ExceptionConfig.CHECKED_EXCEPTION);
    }

    /*
     * Runtime Error (UnChecked Exception) - Runtime Error
     * ex. NullPointer, IllegalArgument, IndexOutOf, Arithmetic 등
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServerErrorResponse<?>> handleRuntimeException(RuntimeException ex) {
        return handleExceptions(ex, ExceptionConfig.UNCHECKED_EXCEPTION);
    }

    private ResponseEntity<ServerErrorResponse<?>> handleExceptions(Exception ex, ExceptionConfig conf) {
        log.error("5XX 예외 발생: {}", ex.getMessage(), ex);

        return ResponseEntity.status(conf.getHttpStatus())
                .body(new ServerErrorResponse<>(conf));
    }

}
