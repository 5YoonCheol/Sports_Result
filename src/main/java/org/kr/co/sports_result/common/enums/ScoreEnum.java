package org.kr.co.sports_result.common.enums;

public enum ScoreEnum {
    HOME_WIN("홈팀 승리"),
    AWAY_WIN("원정팀 승리"),
    DRAW("무승부");

    private final String result;

    ScoreEnum(String result){
        this.result = result;
    }

    public String result(){
        return result;
    }
}
