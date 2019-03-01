package com.example.homework4d;

/**
 * Player
 *
 * Player is essentially what will be in our human player and computer player
 * classes in the game framework. We only care about the player and their score.
 *
 * @Authors: Olivia Dendinger, Sam Lemly, David Campbell, and Kayla Moore
 */

public class Player {

    //initializing variables
    private int playerID;
    private int score;

    /**
     * Player constructor
     * @param id
     */
    public Player(int id){
        this.playerID = id;
    }

    /**
     * setPlayerScore method sets the score of the current player
     * @param newScore
     */
    public void setPlayerScore(int newScore){
        this.score = newScore;
    }

    /**
     * setPlayerID method sets the player's ID
     * @param newID
     */
    public void setPlayerID(int newID){
        this.playerID = newID;
    }

    /**
     * getPlayerID gets the player's ID
     * @return this.playerID
     */
    public int getPlayerID(){
        return this.playerID;
    }

    /**
     * getPlayerScore sets the score of the player
     * @return this.score
     */
    public int getPlayerScore(){
        return this.score;
    }
}
