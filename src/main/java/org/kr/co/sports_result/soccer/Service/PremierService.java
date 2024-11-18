package org.kr.co.sports_result.soccer.Service;

import lombok.RequiredArgsConstructor;
import org.kr.co.sports_result.soccer.Dto.PremierResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PremierService {

    private static final String API_URL = "https://site.web.api.espn.com/apis/site/v2/sports/soccer/scorepanel?league=ENG.1&region=kr&lang=kr&contentorigin=espn&limit=250&dates=20241110";

    public void fetchEplResults() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            System.out.println(restTemplate.getForObject(API_URL, PremierResult.class));


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
