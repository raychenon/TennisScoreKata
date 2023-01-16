package com.raychenon.tennisscore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisScoreRulesTests {

    @Test
    public void testAAB(){
        TennisScoreRules scoreRules = new TennisScoreRules();
        String result = scoreRules.generateScore("AAB");

        assertEquals("""
                Player A : 15 / Player B : Love
                Player A : 30 / Player B : Love
                Player A : 30 / Player B : 15
                """, result);
    }
}
