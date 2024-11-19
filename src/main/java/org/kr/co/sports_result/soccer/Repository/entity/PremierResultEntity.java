package org.kr.co.sports_result.soccer.Repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PremierResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leagueName;   // 리그 이름
    private String eventName;    // 이벤트 이름
    private String venueName;    // 경기장 이름

    private String homeTeam;     // 홈 팀 이름
    private int homeScore;       // 홈 팀 점수

    private String awayTeam;     // 원정 팀 이름
    private int awayScore;       // 원정 팀 점수

    private String result;       // 경기 결과: "Win", "Lose", "Draw"
}
