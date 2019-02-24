package com.example.homework4d;

import java.util.Random;

public class CosmicWimpoutState {
    //instance variables that should encompass all the information
    /**
     * IDEAS
     * Whose turn it is? (int?)
     * Current score of each player (int)
     * current state of dice (create a dice class?) // that has a different int for every face?
     *
     */
    // to satisfy Serializable interface
    private static final long serialVersionUID = 12345678910L;

    private int currentScorePlayer1;
    private int currentScorePlayer2;
    private int currentScorePlayer3;

    private int whoseTurn;
    private int numPlayers;


    private Dice diceNumOne = new Dice(1);
    private Dice diceNumTwo = new Dice(2);
    private Dice diceNumThree = new Dice(3);
    private Dice diceNumFour = new Dice(4);
    private Dice diceNumFive = new Dice(5);

    private Dice diceArray[] = new Dice[5];

    public CosmicWimpoutState(){
        currentScorePlayer1 = 0;
        currentScorePlayer2 = 0;
        currentScorePlayer3 = 0;
        diceArray[0] = diceNumOne;
        diceArray[1] = diceNumTwo;
        diceArray[2] = diceNumThree;
        diceArray[3] = diceNumFour;
        diceArray[4] = diceNumFive;

        whoseTurn = 1;

        for(int i = 0; i < diceArray.length; i++){
            diceArray[i].diceID = i+1;
            diceArray[i].diceState = 0;
        }

        //finish once more instance variables

    }

    //implement a deep copy
    public CosmicWimpoutState(CosmicWimpoutState orig){
        currentScorePlayer1 = orig.currentScorePlayer1;
        currentScorePlayer2 = orig.currentScorePlayer2;
        currentScorePlayer3 = orig.currentScorePlayer3;

        whoseTurn = orig.whoseTurn;

        Dice copyArray[] = new Dice[5];
        for(int i =0; i < diceArray.length; i++){
            copyArray[i] = new Dice(i+1);
            copyArray[i].diceID = orig.diceArray[i].diceID;
            copyArray[i].diceState = orig.diceArray[i].diceState;
        }


    }

    /**
    *toString(); describes the state of a game as a string, prints all the values
     * of all the variables; add @Override tag
    */
    @Override
    public String toString() {
        return "Player1 Score: " + currentScorePlayer1 + "Player2 Score: " + currentScorePlayer2 +
                "Player3 Score: " + currentScorePlayer3 + "Player Turn: " + whoseTurn + "Number of players: " +
                numPlayers + "Dice 1: " + diceNumOne + "Dice 2: " + diceNumTwo + "Dice 3: " + diceNumThree +
                "Dice 4: " + diceNumFour + "Dice 5: " + diceNumFive;
    }

    /**
     add methods for each of the actions - each should have a boolean return value;
     when called, each method should verify the move is a legal move for the current
     game state
     return false; if its illegal
     return true; if its legal (modify the game state if its true \
     pass in player as a parameter

     re-Rolls
     Roll Dice
     End Game
     End Turn
     */

    public boolean RollDice(int playerId){
        if(playerId == whoseTurn) {
            //rolls all dice
            diceArray[0].diceState = (int)(Math.random()*6);
            diceArray[1].diceState = (int)(Math.random()*6);
            diceArray[2].diceState = (int)(Math.random()*6);
            diceArray[3].diceState = (int)(Math.random()*6);
            diceArray[4].diceState = (int)(Math.random()*6);

            return true;
        }
        else{
            // illegal move
            return false;
        }

    }

    public boolean EndGame(int playerId){
        if(playerId == whoseTurn) {
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }

    public boolean EndTurn(int playerId) {
        if(playerId == whoseTurn) {
            if(playerId == 1){
                //add points
                whoseTurn = 2;
            }
            else if(playerId == 2){
                //add points
                whoseTurn = 3;
            }
            else if(playerId == 3){
                //add points
                whoseTurn = 1;
            }
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }
    public boolean ReRollDice(int playerId, int id ){
        if(playerId == whoseTurn) {
            diceArray[id-1].rollMe();
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }


}
