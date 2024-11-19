package org.kr.co.sports_result.common.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    //영국 시간을 한국 시간으로 변환하는 메소드
    public static String getKoreaDate(String datetime){
        // UTC 시간 문자열
        String utcTime = datetime;

        ZonedDateTime utcDateTime = ZonedDateTime.parse(utcTime, DateTimeFormatter.ISO_DATE_TIME);

        // 한국 시간(KST)으로 변환
        ZonedDateTime koreaTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        // 원하는 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedTime = koreaTime.format(formatter);

        return formattedTime;
    }

    // 오늘 날짜를 "yyyyMMdd" 형식의 String으로 반환하는 메소드
    public static String getCurrentDate() {
        // 오늘 날짜를 구하고
        LocalDate today = LocalDate.now();

        // "yyyy-MM-dd" 형식으로 포맷할 수 있는 DateTimeFormatter 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 날짜를 String으로 변환하여 반환
        return today.format(formatter);
    }

    // 오늘 날짜를 "yyyy년 MM월 dd일" 형식의 String으로 변환하는 메소드
    public static String getToday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return today.format(formatter);
    }
}
