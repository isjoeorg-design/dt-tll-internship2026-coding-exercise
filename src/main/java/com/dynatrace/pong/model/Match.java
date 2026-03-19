package com.dynatrace.pong.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Match {
    private Long player1Id;
    private Long player2Id;
    private int player1Score = 0;
    private int player2Score = 0;
    private boolean finished = false;
    private Long winnerId;

    public Match(Long player1Id, Long player2Id) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
    }

    public void player1Scores() {
        if (!isFinished()) {
            player1Score++;
            checkIfFinished();
        }

    }

    public void player2Scores() {
        if (!isFinished()) {
            player2Score++;
            checkIfFinished();
        }
    }

    private void checkIfFinished() {
        if ((player1Score >= 11 || player2Score >= 11) && Math.abs(player1Score - player2Score) >= 2) {
            finished = true;
            winnerId = player1Score > player2Score ? player1Id : player2Id;
        }
    }


}
