package org.kr.co.sports_result.soccer.Controller;

import lombok.RequiredArgsConstructor;
import org.kr.co.sports_result.soccer.Service.PremierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PremierController {
    private final PremierService premierService;

    @GetMapping("api/premier-results")
    public void getPremierResults() {
        premierService.fetchEplResults();
    }
}
