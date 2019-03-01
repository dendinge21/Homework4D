package com.example.homework4d;

/**
 * Player
 *
 * Player is essentially what will be in our human play and computer play
 * classes in the game framework. We only care about which player and their score
 *
 * @Authors: Olivia Dendinger, Sam Lemly, David Campbell, and Kayla Moore
 */
public class Player {
    private int playerID;
    private int score;

    public Player(int id){
        this.playerID = id;
    }
    public void setPlayerScore(int newScore){
        this.score = newScore;
    }
    public void setPlayerID(int newID){
        this.playerID = newID;
    }
    public int getPlayerID(){return this.playerID;}
    public int getPlayerScore(){return this.score;}
}
