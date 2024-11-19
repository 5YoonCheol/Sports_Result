package org.kr.co.sports_result.batch.soccer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SoccerResultScheduler {

    @Scheduled(cron = "*/10 * * * * ?")
    public void soccerResultScheduler() {
        System.out.println("Soccer Result Scheduler Test");
    }
}
