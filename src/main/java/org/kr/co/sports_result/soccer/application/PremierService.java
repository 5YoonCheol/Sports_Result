package org.kr.co.sports_result.soccer.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kr.co.sports_result.common.enums.ScoreEnum;
import org.kr.co.sports_result.common.exception.PremierLeagueDataNotFoundException;
import org.kr.co.sports_result.common.util.DateUtil;
import org.kr.co.sports_result.soccer.domain.dto.PremierResult;
import org.kr.co.sports_result.soccer.infrastructure.PremierRepository;
import org.kr.co.sports_result.soccer.infrastructure.entity.PremierResultEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PremierService {
    private final PremierRepository premierRepository;

    public void fetchEplResults() {
        String apiUrl = "https://site.api.espn.com/apis/site/v2/sports/soccer/scorepanel?league=ENG.1&region=kr&lang=kr&contentorigin=espn&limit=250&dates=" + DateUtil.getCurrentDate();

        RestTemplate restTemplate = new RestTemplate();

        try {
            PremierResult premierResult = restTemplate.getForObject(apiUrl, PremierResult.class);

            if(premierResult.getScores().get(0).getEvents().isEmpty()) {
                log.info(DateUtil.getToday() + " 프리미어 리그 경기가 없습니다.");
                throw new PremierLeagueDataNotFoundException(DateUtil.getToday() + " 프리미어 리그 경기가 없습니다.");
            }
            savePremierResult(premierResult);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void savePremierResult(PremierResult premierResult) {
        // DTO를 엔티티로 매핑
        premierResult.getScores().get(0).getEvents().forEach(event -> {
            event.getCompetitions().forEach(competition -> {
                PremierResult.Event.Competitor homeTeam = competition.getCompetitors().stream()
                        .filter(c -> "home".equals(c.getHomeAway()))
                        .findFirst()
                        .orElse(null);

                PremierResult.Event.Competitor awayTeam = competition.getCompetitors().stream()
                        .filter(c -> "away".equals(c.getHomeAway()))
                        .findFirst()
                        .orElse(null);

                if (homeTeam != null && awayTeam != null) {
                    PremierResultEntity resultEntity = new PremierResultEntity();
                    resultEntity.setEventName(event.getName());
                    resultEntity.setEventStartDate(DateUtil.getKoreaDate(competition.getStartDate()));
                    resultEntity.setEventEndDate(DateUtil.getKoreaDate(DateUtil.calculateEndDateTime(competition.getStartDate(), event.getStatus().getDisplayClock())));

                    resultEntity.setHomeTeam(homeTeam.getTeam().getDisplayName());
                    resultEntity.setAwayTeam(awayTeam.getTeam().getDisplayName());
                    resultEntity.setHomeScore(Integer.parseInt(homeTeam.getScore()));
                    resultEntity.setAwayScore(Integer.parseInt(awayTeam.getScore()));

                    if (homeTeam.isWinner()) {
                        resultEntity.setResult(ScoreEnum.HOME_WIN.result());
                    } else if (awayTeam.isWinner()) {
                        resultEntity.setResult(ScoreEnum.AWAY_WIN.result());
                    } else {
                        resultEntity.setResult(ScoreEnum.DRAW.result());
                    }

                    // DB 저장
                    premierRepository.save(resultEntity);
                }
            });
        });
    }
}
