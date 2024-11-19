package org.kr.co.sports_result.common.util;

import java.time.*;
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

    // 추가 시간을 경기 시작 시간에 더하여 경기 종료 시간을 구한 후 String으로 변환하는 메소드
    public static String calculateEndDateTime(String startDate, String displayClock) {
        // 1. 시작 시간을 ZonedDateTime으로 변환
        ZonedDateTime startDateTime = ZonedDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);

        // 2. 정규시간과 추가시간 파싱
        int regularMinutes = 0;
        int additionalMinutes = 0;

        if (displayClock.contains("+")) {
            String[] parts = displayClock.split("\\+");
            regularMinutes = Integer.parseInt(parts[0].replace("'", "").trim());
            additionalMinutes = Integer.parseInt(parts[1].replace("'", "").trim());
        } else {
            regularMinutes = Integer.parseInt(displayClock.replace("'", "").trim());
        }

        // 3. 총 경과 시간 계산
        int totalMinutes = regularMinutes + additionalMinutes;

        // 4. 시작 시간에 총 경과 시간 추가
        ZonedDateTime endDateTime = startDateTime.plus(Duration.ofMinutes(totalMinutes));

        // 5. 최종 결과 시간 ISO 8601 형식으로 변환
        return endDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
