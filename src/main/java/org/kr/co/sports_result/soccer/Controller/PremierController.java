package org.kr.co.sports_result.soccer.Controller;

import lombok.RequiredArgsConstructor;
import org.kr.co.sports_result.soccer.Service.PremierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PremierController {
    private final PremierService premierService;

    @GetMapping("api/premier-results")
    public ResponseEntity<String> getPremierResults() {
        premierService.fetchEplResults();

        return ResponseEntity.ok("EPL 결과를 성공적으로 가져왔습니다.");
    }
}
