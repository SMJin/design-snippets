package org.example.common.response.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum ExceptionConfig {

    TEST_ERROR(10000, HttpStatus.BAD_REQUEST, "테스트 에러"), //테스트

    // 400 잘못된 요청
    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, Message.BAD_REQUEST),
    ILLEGAL_ARGUMENT(40001, HttpStatus.BAD_REQUEST, Message.ILLEGAL_ARGUMENT),
    HTTP_MESSAGE_NOT_READABLE(40002, HttpStatus.BAD_REQUEST, Message.HTTP_MESSAGE_NOT_READABLE), //잘못된 요청

    // 401 인증 필요(로그인 관련)
    UNAUTHORIZED(40100, HttpStatus.UNAUTHORIZED, Message.UNAUTHORIZED), // 401 default
    USER_NOT_EXIST(40101, HttpStatus.UNAUTHORIZED, Message.USER_NOT_EXIST), // 사용자 정보 없음
//    SESSION_INVALID(40102, HttpStatus.UNAUTHORIZED, Message.SESSION_INVALID), // 세션 만료
    TOKEN_NOT_EXIST(40103, HttpStatus.UNAUTHORIZED, Message.TOKEN_NOT_EXIST),   // 토큰이 없음
    TOKEN_INVALID(40104, HttpStatus.UNAUTHORIZED, Message.TOKEN_INVALID),   // 잘못된 토큰

    // 403 Forbidden 접근 금지(로그인은 되었으나 API 권한이 없음)
    FORBIDDEN(40300, HttpStatus.FORBIDDEN, Message.FORBIDDEN),
//    USER_DISABLED(40301, HttpStatus.FORBIDDEN, Message.USER_DISABLED),

//    LOGIN_INFO_NOT_CORRECT(40101, HttpStatus.UNAUTHORIZED, Message.LOGIN_INFO_NOT_CORRECT),
//    USER_LOCKED(40103, HttpStatus.UNAUTHORIZED, Message.USER_LOCKED),
//    PASSWORD_NOT_CORRECT(40105, HttpStatus.UNAUTHORIZED, Message.PASSWORD_NOT_CORRECT),
//    MAX_LOGIN_RETRY_LIMIT(40106, HttpStatus.UNAUTHORIZED, Message.MAX_LOGIN_RETRY_LIMIT),
//    PASSWORD_CHANGE_REQUEST(40107, HttpStatus.UNAUTHORIZED, Message.PASSWORD_CHANGE_REQUEST),
//    PASSWORD_EXPIRE_DATE(40108, HttpStatus.UNAUTHORIZED, Message.PASSWORD_EXPIRE_DATE),

    // 404 리소스 없음(존재하지 않는 URL)
    NOT_FOUND(40400, HttpStatus.NOT_FOUND, Message.NOT_FOUND),
    NULL_POINTER(40401, HttpStatus.NOT_FOUND, Message.NULL_POINTER),
//    URL_NOT_FOUND(40402, HttpStatus.NOT_FOUND, Message.URL_NOT_FOUND),
//    NO_CONTENT(40403, HttpStatus.NOT_FOUND, Message.NO_CONTENT),

    // 405 잘못된 HTTP 메서드(GET/POST 등 방식 오류)
    METHOD_TYPE_ERROR(40500, HttpStatus.METHOD_NOT_ALLOWED, Message.METHOD_NOT_ALLOWED),

    // 409 리소스 충돌(중복된 회원가입 등)
    CONFLICT(40900, HttpStatus.CONFLICT, Message.CONFLICT),
    DUPLICATED(40901, HttpStatus.CONFLICT, Message.DUPLICATED),
    DELETE_NOT_ALLOWED(40902, HttpStatus.CONFLICT, Message.DELETE_NOT_ALLOWED),
    FOREIGN_KEY_CONFLICT(40903, HttpStatus.CONFLICT, Message.FOREIGN_KEY_CONFLICT),

    // 415 지원하지 않는 미디어 타입(JSON API 에 XML 요청 등)
    MEDIA_TYPE_ERROR(41500, HttpStatus.UNSUPPORTED_MEDIA_TYPE, Message.UNSUPPORTED_MEDIA_TYPE),
    IMPORT_EXCEL_FAILURE(41501, HttpStatus.INTERNAL_SERVER_ERROR, Message.IMPORT_EXCEL_FAILURE), //엑셀 업로드 오류

    // 5XX (서버에러)
    CHECKED_EXCEPTION(50000, HttpStatus.INTERNAL_SERVER_ERROR, Message.CHECKED_EXCEPTION),
    UNCHECKED_EXCEPTION(50001, HttpStatus.INTERNAL_SERVER_ERROR, Message.UNCHECKED_EXCEPTION),
    NOT_IMPLEMENTED(50100, HttpStatus.NOT_IMPLEMENTED, Message.NOT_IMPLEMENTED),
    BAD_GATEWAY(50200, HttpStatus.BAD_GATEWAY, Message.BAD_GATEWAY),
    SERVICE_UNAVAILABLE(50300, HttpStatus.SERVICE_UNAVAILABLE, Message.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT(50400, HttpStatus.GATEWAY_TIMEOUT, Message.GATEWAY_TIMEOUT),
    ;

    public static final int LOGIN_RETRY_LIMIT = 5;
    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    public static class Message {

        // 400 BadRequest
        public static final String BAD_REQUEST = "잘못된 요청입니다.";
        public static final String ILLEGAL_ARGUMENT = "요청 데이터가 유효하지 않습니다(잘못된 요청입니다).";
        public static final String HTTP_MESSAGE_NOT_READABLE = "JSON 객체가 잘못되어 요청을 읽을 수 없습니다.";

        // 401 Unauthorized 인증 실패(로그인 관련)
        public static final String UNAUTHORIZED = "사용자 인증에 실패하였습니다.";

        /*
            검토 필요
         */
        // 이렇게 까지 세세하게 필요한지 검토 필요
        public static final String USER_NOT_EXIST = "사용자 정보가 존재하지 않습니다.";
//        public static final String SESSION_INVALID = "세션이 종료되었습니다. 재로그인 해주십시오.";
        public static final String TOKEN_NOT_EXIST = "인증 토큰이 존재하지 않습니다.";
        public static final String TOKEN_INVALID = "인증 토큰이 유효하지 않습니다.";

        // 403 Forbidden 접근 금지(로그인은 되었으나 권한이 없음)
        public static final String FORBIDDEN = "권한이 없습니다."; // UPDATE, DELETE 등
//        public static final String USER_DISABLED = "접근 권한이 없는 사용자입니다.";

        /*
         ***** 검토필요 *****
         */
//        // JWT 로그인 관련
//        public static final String PASSWORD_NOT_CORRECT = "비밀번호가 일치하지 않습니다. 로그인에 " + LOGIN_RETRY_LIMIT + "번 실패하면 계정이 잠깁니다.";
//        public static final String MAX_LOGIN_RETRY_LIMIT = "로그인 시도 횟수(" + LOGIN_RETRY_LIMIT + "회)를 초과했습니다.";
//        public static final String PASSWORD_CHANGE_REQUEST = "관리자의 요청으로 비밀번호를 변경하셔야 합니다.";
//        public static final String PASSWORD_EXPIRE_DATE = "비밀번호 기간이 만료되었습니다.";
//        public static final String USER_LOCKED = "계정이 잠겼습니다. 시스템 관리자에게 문의하세요.";
//        public static final String LOGIN_SUCCESS = "로그인에 성공했습니다.";
//        public static final String LOGOUT_SUCCESS = "로그아웃 되었습니다.";
//        public static final String LOGIN_INFO_NOT_CORRECT = "사용자 정보나 비밀번호가 올바르지 않습니다. 로그인에 " + LOGIN_RETRY_LIMIT + "번 실패하면 계정이 잠깁니다.";

        // 404 NotFound
        public static final String NOT_FOUND = "요청한 리소스를 찾을 수 없습니다.";   // READ, UPDATE 등
        public static final String NULL_POINTER = "요청한 리소스가 NULL 입니다.";
//        public static final String URL_NOT_FOUND = "요청 URL이 존재하지 않습니다.";
//        public static final String NO_CONTENT = "데이터가 존재하지 않습니다.";  // READ, DELETE 등

        // 405 MethodNotAllowed
        public static final String METHOD_NOT_ALLOWED = "지원되지 않는 HTTP 메서드입니다(GET/POST 등).";

        // 409 Conflict 리소스 충돌(중복된 회원가입 등)
        public static final String CONFLICT = "리소스가 충돌되었습니다. 작업을 수행할 수 없습니다.";
        public static final String DUPLICATED = "중복된 데이터 입니다."; // CREATE 등
        public static final String DELETE_NOT_ALLOWED = "데이터를 삭제할 수 없습니다(연결된 데이터가 있을 수 있습니다).";
        public static final String FOREIGN_KEY_CONFLICT = "참조 무결성 제약조건 위반시에 발생하는 에러가 발생했습니다. 각 DB에서 컬럼간 참조관계를 검토하십시오.";

        // 415 UnsupportedMediaType
        public static final String UNSUPPORTED_MEDIA_TYPE = "지원하지 않는 데이터 형식(미디어 포맷)입니다.";
        public static final String IMPORT_EXCEL_FAILURE = "사용할 수 있는 양식이 아닙니다."; // 엑셀파일 읽기 관련


        /*
            ***** 검토필요 *****
         */
//        public static final String INSERT_FAILURE = "데이터를 등록하는데 문제가 발생했습니다.";
//        public static final String SAVE_FAILURE = "데이터를 저장하는데 문제가 발생했습니다.";
//        public static final String DELETE_FAILURE = "데이터를 삭제하는데 문제가 발생했습니다.";
        public static final String SAVE_ITEM_NOT_EXIST = "저장할 데이터가 존재하지 않습니다.";

        // 5XX 공통 에러
        public static final String CHECKED_EXCEPTION = "서버 내부 오류가 발생했습니다."; // 50000
        public static final String UNCHECKED_EXCEPTION = "예상치 못한 오류가 발생했습니다."; // 50001
        public static final String NOT_IMPLEMENTED = "이 기능은 아직 개발되지 않았습니다.";    // 501
        public static final String BAD_GATEWAY = "현재 요청을 처리할 수 없습니다(외부 API 응답 오류).";    // 502
        public static final String SERVICE_UNAVAILABLE = "현재 서버 점검 중입니다.";  // 503
        public static final String GATEWAY_TIMEOUT = "API 응답 시간이 초과되고 있습니다.";   // 504

    }

    private static final Map<Integer, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(ExceptionConfig::getCode, ExceptionConfig::name)));

    public static ExceptionConfig of(final String code) {
        return ExceptionConfig.valueOf(CODE_MAP.get(Integer.parseInt(code)));
    }
}
