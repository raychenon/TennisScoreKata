package com.raychenon.tennisscore;

import java.text.MessageFormat;

public class TennisScoreRules {
    private ScoreRepository repository;

    public String generateScore(String input) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == 'A') markPointForPlayer(Player.A);
            else if (ch == 'B') markPointForPlayer(Player.B);

            str.append(makeSentenceOnCurrentScore()).append("\n");
        }
        return str.toString();
    }

    public TennisScoreRules() {
        this.repository = new ScoreRepository();
    }

    public TennisScoreRules(ScoreRepository repository) {
        this.repository = repository;
    }


    protected void markPointForPlayer(Player player) {
        if (player == Player.A) {
            repository.incrementScoreA();
        } else if (player == Player.B) {
            repository.incrementScoreB();
        }
    }

    /**
     * “Player A : 15 / Player B : 0”
     * “Player A : 15 / Player B : 15”
     *
     * @return
     */
    private String makeSentenceOnCurrentScore() {
        String result = MessageFormat.format("Player A : {0} / Player B : {1}", translateScoreToString(repository.getScoreA()), translateScoreToString(repository.getScoreB()));
        return result;
    }

    private String translateScoreToString(int score) throws IllegalArgumentException {
        return switch (score) {
            case 3 -> "40";
            case 2 -> "30";
            case 1 -> "15";
            case 0 -> "Love";
            default -> throw new IllegalArgumentException("Illegal score: " + score);
        };
    }

}
