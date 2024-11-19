package org.kr.co.sports_result.soccer.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kr.co.sports_result.common.exception.PremierLeagueDataNotFoundException;
import org.kr.co.sports_result.common.util.DateUtil;
import org.kr.co.sports_result.soccer.Dto.PremierResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PremierService {

    public void fetchEplResults() {
        String apiUrl = "https://site.web.api.espn.com/apis/site/v2/sports/soccer/scorepanel?league=ENG.1&region=kr&lang=kr&contentorigin=espn&limit=250&dates=" + DateUtil.getCurrentDate();

        RestTemplate restTemplate = new RestTemplate();

        try {
            PremierResult premierResult = restTemplate.getForObject(apiUrl, PremierResult.class);

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
}
