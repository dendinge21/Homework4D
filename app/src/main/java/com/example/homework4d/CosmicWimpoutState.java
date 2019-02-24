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

    public CosmicWimpoutState(int currentScore1, int currentScore2, int currentScore3, int playerTurn,
                              Dice numOne, Dice numTwo, Dice numThree, Dice numFour, Dice numFive){
        currentScorePlayer1 = 0;
        currentScorePlayer2 = 0;
        currentScorePlayer3 = 0;

        whoseTurn = playerTurn;

        diceNumOne.diceState = 1;
        diceNumOne.diceID = 1;
        diceNumTwo.diceState = numTwo.diceState;
        diceNumTwo.diceID = 2;
        diceNumThree.diceState = numThree.diceState;
        diceNumThree.diceID = 3;
        diceNumFour.diceState = numFour.diceState;
        diceNumFour.diceID = 4;
        diceNumFive.diceState = numFive.diceState;
        diceNumFive.diceID = 5;

        //finish once more instance variables

    }

    //implement a deep copy
    public CosmicWimpoutState(CosmicWimpoutState orig){
        currentScorePlayer1 = orig.currentScorePlayer1;
        currentScorePlayer2 = orig.currentScorePlayer2;
        currentScorePlayer3 = orig.currentScorePlayer3;

        whoseTurn = orig.whoseTurn;

        diceNumOne.diceState = orig.diceNumOne.diceState;
        diceNumOne.diceID = orig.diceNumOne.diceID;
        diceNumTwo.diceState = orig.diceNumTwo.diceState;
        diceNumTwo.diceID = orig.diceNumTwo.diceID;
        diceNumThree.diceState = orig.diceNumThree.diceState;
        diceNumThree.diceID = orig.diceNumThree.diceID;
        diceNumFour.diceState = orig.diceNumFour.diceState;
        diceNumFour.diceID = orig.diceNumFour.diceID;
        diceNumFive.diceState = orig.diceNumFive.diceState;
        diceNumFive.diceID = orig.diceNumFive.diceID;


    }

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

     re-Rolls
     Roll Dice
     End Game
     End Turn
     */

    public boolean RollDice(int playerId){
        int rand0, rand1, rand2, rand3, rand4;
        if(playerId == whoseTurn) {
            //rolls all dice
            rand0 = (int)(Math.random()*6);
            rand1 = (int)(Math.random()*6);
            rand2 = (int)(Math.random()*6);
            rand3 = (int)(Math.random()*6);
            rand4 = (int)(Math.random()*6);

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
    public boolean ReRollDice(int playerId, int diceId){
        if(playerId == whoseTurn) {

            return true;
        }
        else{
            // illegal move
            return false;
        }
    }


}
