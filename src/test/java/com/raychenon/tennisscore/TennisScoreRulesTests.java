package com.raychenon.tennisscore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisScoreRulesTests {

    @Test
    public void testAAB() {
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("AAB");

        assertEquals("""
                Player A : 15 / Player B : Love
                Player A : 30 / Player B : Love
                Player A : 30 / Player B : 15
                """, result);
    }

    @Test
    public void testABABAA() {
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("ABABAA");

        assertEquals("""
                Player A : 15 / Player B : Love
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A wins the game
                """, result);
    }

    @Test
    public void testBABABBInverted() {
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("BABABB");

        assertEquals("""
                Player A : Love / Player B : 15
                Player A : 15 / Player B : 15
                Player A : 15 / Player B : 30
                Player A : 30 / Player B : 30
                Player A : 30 / Player B : 40
                Player B wins the game
                """, result);
    }

    @Test
    public void givenAlternatingSequence_ThenDeuce() {
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("ABABABABABAB");

        assertEquals("""
                Player A : 15 / Player B : Love
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                Advantage, player A
                Deuce
                Advantage, player A
                Deuce
                Advantage, player A
                Deuce
                """, result);
    }

    @Test
    public void givenAlternatingSequence_WhenOnePlayerLeads_ThenDeuceAdvantage() {
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("ABABABABBAABAA");

        assertEquals("""
                Player A : 15 / Player B : Love
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                Advantage, player A
                Deuce
                Advantage, player B
                Deuce
                Advantage, player A
                Deuce
                Advantage, player A
                Player A wins the game
                """, result);
    }


    @Test
    public void testAAAAAWithTooManyWins() {
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("AAAAA");

        // stop the earlier ?
        assertEquals("""
                Player A : 15 / Player B : Love
                Player A : 30 / Player B : Love
                Player A : 40 / Player B : Love
                Player A wins the game
                Player A wins the game
                """, result);
    }


    @Test
    public void givenPlayerAstartsWith2Points_When_ThenTestBBAA() {
        TennisScoreRules scoreRules = new TennisScoreRules(new ScoreRepository(2,0));
        String result = scoreRules.generateScore("BBAA");

        assertEquals("""
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A wins the game
                """, result);
    }
}
