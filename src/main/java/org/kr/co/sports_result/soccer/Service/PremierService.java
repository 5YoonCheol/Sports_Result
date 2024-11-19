package org.kr.co.sports_result.soccer.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kr.co.sports_result.common.exception.PremierLeagueDataNotFoundException;
import org.kr.co.sports_result.common.util.DateUtil;
import org.kr.co.sports_result.soccer.Dto.PremierResult;
import org.kr.co.sports_result.soccer.Repository.PremierRepository;
import org.kr.co.sports_result.soccer.Repository.entity.PremierResultEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PremierService {
    private final PremierRepository premierRepository;

    public void fetchEplResults() {
        String apiUrl = "https://site.api.espn.com/apis/site/v2/sports/soccer/scorepanel?league=ENG.1&region=kr&lang=kr&contentorigin=espn&limit=250&dates=241110";

        RestTemplate restTemplate = new RestTemplate();

        try {
            PremierResult premierResult = restTemplate.getForObject(apiUrl, PremierResult.class);
            System.out.println(premierResult.toString());
            savePremierResult(premierResult);
            if(premierResult.getScores().get(0).getEvents().isEmpty()) {
                log.info(DateUtil.getToday() + " 프리미어 리그 경기가 없습니다.");
                throw new PremierLeagueDataNotFoundException(DateUtil.getToday() + " 프리미어 리그 경기가 없습니다.");
            }

            premierResult.getScores().get(0).getEvents().forEach(event -> {
               System.out.println(event.getCompetitions().toString());
               event.getCompetitions().forEach(competition -> {
                   System.out.println(DateUtil.getKoreaDate(competition.getStartDate()));
               });
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

        public void savePremierResult(PremierResult premierResult) {
            List<PremierResultEntity> entities = new ArrayList<>();

            // DTO를 엔티티로 매핑
            for (PremierResult.Score score : premierResult.getScores()) {
                for (PremierResult.Event event : score.getEvents()) {
                    for (PremierResult.Event.Competitor competitor : event.getCompetitions().get(0).getCompetitors()) {
                        PremierResultEntity entity = new PremierResultEntity();

                        // 기본 정보 매핑
                        entity.setLeagueName(score.getLeagues().get(0).getName()); // 첫 번째 리그 이름 사용
                        entity.setEventName(event.getName());
                        entity.setVenueName(event.getVenue().getFullName());

                        // 팀 정보 매핑
                        PremierResult.Event.Competitor home = event.getCompetitions().get(0).getCompetitors().stream()
                                .filter(c -> "home".equals(c.getHomeAway()))
                                .findFirst()
                                .orElse(null);

                        PremierResult.Event.Competitor away = event.getCompetitions().get(0).getCompetitors().stream()
                                .filter(c -> "away".equals(c.getHomeAway()))
                                .findFirst()
                                .orElse(null);

                        if (home != null && away != null) {
                            entity.setHomeTeam(home.getTeam().getDisplayName());
                            entity.setHomeScore(Integer.parseInt(home.getScore()));
                            entity.setAwayTeam(away.getTeam().getDisplayName());
                            entity.setAwayScore(Integer.parseInt(away.getScore()));

                            // 승패 계산
                            if (entity.getHomeScore() > entity.getAwayScore()) {
                                entity.setResult("Win");
                            } else if (entity.getHomeScore() < entity.getAwayScore()) {
                                entity.setResult("Lose");
                            } else {
                                entity.setResult("Draw");
                            }

                            entities.add(entity);
                        }
                    }
                }
            }

            // 엔티티 저장
            premierRepository.saveAll(entities);
        }
}
