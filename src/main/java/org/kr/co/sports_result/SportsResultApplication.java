package org.kr.co.sports_result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SportsResultApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsResultApplication.class, args);
    }

}
