package com.dynatrace.pong.service;

import com.dynatrace.pong.exception.PlayerNotFoundException;
import com.dynatrace.pong.model.Match;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MatchService {
    private final PlayerService playerService;
    private final Map<Long, Match> matches = new HashMap<>();

    public MatchService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Match startMatch(Long player1Id, Long player2Id) {
        if (!playerService.playerExists(player1Id) || !playerService.playerExists(player2Id)) {
            throw new PlayerNotFoundException(player1Id);
        }
        Match match = new Match(player1Id, player2Id);
        matches.put(player1Id, match);
        matches.put(player2Id, match);
        return match;
    }

    public Match getMatch(Long playerId) {
        return matches.get(playerId);
    }

    public void playerScores(Long playerId) {
        Match match = matches.get(playerId);
        if (match == null) {
            throw new IllegalArgumentException("Match not found for player: " + playerId);
        }
        if (playerId.equals(match.getPlayer1Id())) {
            match.player1Scores();
        } else if (playerId.equals(match.getPlayer2Id())) {
            match.player2Scores();
        }
    }
}
