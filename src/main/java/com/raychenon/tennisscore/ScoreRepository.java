package com.raychenon.tennisscore;

/**
 * In memory repository
 */
public class ScoreRepository {
    private int scoreB;
    private int scoreA;

    public ScoreRepository() {
        this.scoreA = 0;
        this.scoreB = 0;
    }

    /**
     * possible to mock up the score
     * @param scoreA
     * @param scoreB
     */
    public ScoreRepository(int scoreA, int scoreB) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public int getScoreB() {
        return scoreB;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void incrementScoreA() {
        this.scoreA++;
    }

    public void incrementScoreB() {
        this.scoreB++;
    }
}
