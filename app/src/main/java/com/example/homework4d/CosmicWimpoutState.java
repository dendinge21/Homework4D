package com.example.homework4d;

public class CosmicWimpoutState {
    //instance variables that should encompass all the information
    /**
     * IDEAS
     * Whose turn it is? (player)
     * Current score of each player (int)
     * current state of dice (create a dice class?)
     *
     */
    private int currentScorePlayer1;
    private int currentScorePlayer2;
    private int currentScorePlayer3;

    public CosmicWimpoutState(int currentScore1, int currentScore2, int currentScore3){
        currentScorePlayer1 = currentScore1;
        currentScorePlayer2 = currentScore2;
        currentScorePlayer3 = currentScore3;

        //finish once more instance variables

    }

    //implement a deep copy

    /**
    *toString(); describes the state of a game as a string, prints all the values
     * of all the variables; add @Override tag
    */

    /**
     add methods for each of the actions - each should have a boolean return value;
     when called, each method should verify the move is a legal move for the current
     game state
     return false; if its illegal
     return true; if its legal (modify the game state if its true \
     pass in player as a parameter
     */

}
