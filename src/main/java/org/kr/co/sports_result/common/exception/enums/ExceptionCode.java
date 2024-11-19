package org.kr.co.sports_result.common.exception.enums;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ExceptionCode {
    // 400 Bad Request (잘못된 요청)
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "잘못된 요청입니다."),

    // 401 Unauthorized (인증 실패)
    UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED_EXCEPTION", "인증 정보 없음"),

    // 403 Forbidden (접근 거부)
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "권한이 없습니다."),

    // 404 Not Found (리소스 없음)
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "요청한 리소스를 찾을 수 없습니다."),

    // 409 Conflict (데이터 충돌)
    CONFLICT(HttpStatus.CONFLICT, "CONFLICT", "데이터 충돌이 발생했습니다."),

    // 5xx
    NO_SEARCH_RESULT_EXCEPTION(HttpStatus.NOT_EXTENDED, "NO_SEARCH_RESULT_EXCEPTION", "검색 결과가 없습니다."),

    // 500 Internal Server Error (서버 내부 오류)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."),

    // 502 Bad Gateway (게이트웨이 오류)
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "BAD_GATEWAY", "서버와의 통신에 오류가 발생했습니다."),

    // 503 Service Unavailable (서비스 이용 불가)
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "SERVICE_UNAVAILABLE", "서비스를 이용할 수 없습니다."),

    // 1001 Invalid Input (입력 값 오류)
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "INVALID_INPUT", "입력 값이 유효하지 않습니다."),

    // 1002 User Not Found (사용자 없음)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),

    // 1003 Database Error (데이터베이스 오류)
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DATABASE_ERROR", "데이터베이스 오류가 발생했습니다."),

    // 1004 Invalid File Format (파일 형식 오류)
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST, "INVALID_FILE_FORMAT", "파일 형식이 올바르지 않습니다."),

    // 9999 Unexpected Error (예상치 못한 오류)
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED_ERROR", "예상치 못한 오류가 발생했습니다."),

    // 2001 API Timeout (API 타임아웃)
    API_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "API_TIMEOUT", "API 응답 시간이 초과되었습니다."),

    // 2002 API Rate Limit (API 호출 제한 초과)
    API_RATE_LIMIT(HttpStatus.TOO_MANY_REQUESTS, "API_RATE_LIMIT", "API 호출 제한을 초과했습니다."),

    // 3001 Field Validation Error (입력 필드 검증 오류)
    FIELD_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "FIELD_VALIDATION_ERROR", "입력 필드 검증 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
