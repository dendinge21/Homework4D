package com.example.homework4d;

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
