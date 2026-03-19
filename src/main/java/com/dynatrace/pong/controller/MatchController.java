package com.dynatrace.pong.controller;

import com.dynatrace.pong.model.Match;
import com.dynatrace.pong.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/start")
    public ResponseEntity<Match> startMatch(@RequestParam Long player1Id, @RequestParam Long player2Id) {
        Match match = matchService.startMatch(player1Id, player2Id);
        return ResponseEntity.ok(match);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Match> getMatch(@PathVariable Long playerId) {
        return ResponseEntity.ok(matchService.getMatch(playerId));
    }

    @PostMapping("/{playerId}/score")
    public ResponseEntity<Match> playerScores(@PathVariable Long playerId) {
        matchService.playerScores(playerId);
        return ResponseEntity.ok(matchService.getMatch(playerId));
    }
}
