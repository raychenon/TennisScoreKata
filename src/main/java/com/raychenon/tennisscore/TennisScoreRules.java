package com.raychenon.tennisscore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class TennisScoreRules {

    private static Logger logger = LoggerFactory.getLogger(TennisScoreRules.class);

    private ScoreRepository repository;

    public String generateScore(String input) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == 'A') markPointForPlayer(Player.A);
            else if (ch == 'B') markPointForPlayer(Player.B);

            str.append(makeSentenceOnCurrentScore()).append("\n")
                    .append(debugScore());

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


    protected boolean hasWinner() {
        if ((repository.getScoreB() >= 4 && repository.getScoreB() >= repository.getScoreA() + 2) ||
            (repository.getScoreA() >= 4 && repository.getScoreA() >= repository.getScoreB() + 2)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Comment on the current score ( score, Deuce, Advantage, Player wins)
     * return a string on the current score state
     */
    private String makeSentenceOnCurrentScore() {
        if (hasWinner()) {
            return MessageFormat.format("Player {0} wins the game", whoIsWinning());
        } else if (isDeuceAdvantage()) {
            return MessageFormat.format("Advantage, player {0}", whoIsWinning());
        }
        if (isDeuce()) {
            return "Deuce";
        }

        return MessageFormat.format("Player A : {0} / Player B : {1}", translateScoreToString(repository.getScoreA()), translateScoreToString(repository.getScoreB()));
    }

    private String debugScore(){
        var str = " |> scoreA = " + repository.getScoreA() + " , scoreB = " + repository.getScoreB();
        logger.info(str);
        // return str + "\n";
        return "";
    }

    private Player whoIsWinning() {
        return (repository.getScoreA() > repository.getScoreB()) ? Player.A : Player.B;
    }

    /**
     * If both players have 40 points the players are “deuce”.
     * If the game is in deuce, the winner of the ball will have advantage
     * @return Deuce mode or not
     */
    private boolean isDeuce() {
        return repository.getScoreA()  >= 3 && repository.getScoreB()  >= 3;
    }

    /**
     * If the player with advantage wins the ball he wins the game
     * If the player without advantage wins the ball they are back at “deuce”.
     */
    private boolean isDeuceAdvantage() {
        if ((repository.getScoreA() >= 4 && repository.getScoreA() == repository.getScoreB() + 1) ||
            (repository.getScoreB() >= 4 && repository.getScoreB() == repository.getScoreA() + 1)
        ) {
            return true;
        } else {
            return false;
        }
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
