package org.kr.co.sports_result.common.exception;

import org.kr.co.sports_result.common.exception.enums.ExceptionCode;

public class PremierLeagueDataNotFoundException extends PremierLeagueException {
    private final ExceptionCode exceptionCode;

    public PremierLeagueDataNotFoundException(String message) {
        super(message);
        this.exceptionCode = ExceptionCode.NO_SEARCH_RESULT_EXCEPTION;
    }

    public PremierLeagueDataNotFoundException() {
        super();
        this.exceptionCode = ExceptionCode.NO_SEARCH_RESULT_EXCEPTION;
    }

    public ExceptionCode getExceptionCode() { return exceptionCode; }
}
