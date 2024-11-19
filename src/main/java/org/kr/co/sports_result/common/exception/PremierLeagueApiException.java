package org.kr.co.sports_result.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class PremierLeagueApiException extends RuntimeException {
    private final HttpStatus httpStatus;

    public PremierLeagueApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
