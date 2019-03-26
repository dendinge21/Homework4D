package com.example.homework4d;

import org.junit.Test;

import static org.junit.Assert.*;

public class CosmicWimpoutStateTest {


    @Test
    public void endGame() throws Exception {
        CosmicWimpoutState testState= new CosmicWimpoutState();
        boolean result = testState.endGame(1);
        assertTrue("expected game to be over", result);
    }

    @Test
    public void endTurn() throws Exception {
        CosmicWimpoutState testState= new CosmicWimpoutState();
        boolean result = testState.endTurn(1);
        int whoseTurn = testState.getWhoseTurn();
        int turnScore = testState.getTurnScore();
        assertTrue("should be player 2 turn", result);
        assertEquals(2, whoseTurn,0);
        assertEquals(0, turnScore,0);

    }


    @Test
    public void getDiceVal() throws Exception {
        CosmicWimpoutState testState= new CosmicWimpoutState();
        String diceVal = testState.getDiceVal(1);
        assertEquals("should be tens", "Tens", diceVal);
    }

    @Test
    public void getWhoseTurn() throws Exception {
        CosmicWimpoutState testState= new CosmicWimpoutState();
        int whoseTurn = testState.getWhoseTurn();
        assertEquals(1, whoseTurn, 0);

    }

    @Test
    public void totalDiceScore1() throws Exception{
        //tests supernova
        CosmicWimpoutState testState= new CosmicWimpoutState();
        int score = testState.totalDiceScore(testState.getDiceArray(),1);
        assertEquals(-1, score, 0);
    }

    @Test
    public void totalDiceScore2() throws Exception{
        //tests all 2's
        CosmicWimpoutState testState= new CosmicWimpoutState();
        testState.setDiceState(2,0);
        testState.setDiceState(2,1);
        testState.setDiceState(2,2);
        testState.setDiceState(2,3);
        testState.setDiceState(2,4);

        int score = testState.totalDiceScore(testState.getDiceArray(),1);
        assertEquals(200 ,score, 0);
    }
}