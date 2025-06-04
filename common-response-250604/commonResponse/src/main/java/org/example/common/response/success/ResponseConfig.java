package org.example.common.response.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum ResponseConfig {

    // 200 OK
    OK(20000, HttpStatus.OK, Message.OK),
    READ(20001, HttpStatus.OK, Message.READ),
    UPDATE(20002, HttpStatus.OK, Message.UPDATE),
    VERIFIED(20003, HttpStatus.OK, Message.VERIFIED),

    // 201 Created
    CREATED(20100, HttpStatus.CREATED, Message.CREATED),

    // 202 Accepted
    ACCEPTED(20200, HttpStatus.ACCEPTED, Message.ACCEPTED),

    // 204 NoContent
    NO_CONTENT(20400, HttpStatus.NO_CONTENT, Message.NO_CONTENT),
    DELETE(20401, HttpStatus.NO_CONTENT, Message.DELETE),
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    public static class Message {
        // default 200
        public static final String OK = "정상적으로 처리되었습니다.";
        public static final String READ = "정상적으로 조회되었습니다.";
        public static final String UPDATE = "정상적으로 수정되었습니다.";
        public static final String VERIFIED = "정상적으로 인증되었습니다.";

        // 201 Created
        public static final String CREATED = "정상적으로 등록되었습니다.";

        // 202 Accepted
        public static final String ACCEPTED = "(비동기)요청이 수락되었습니다.";

        // 204 NoContent
        public static final String NO_CONTENT = "변경 사항이 없습니다.";
        public static final String DELETE = "정상적으로 삭제되었습니다.";
    }
}
