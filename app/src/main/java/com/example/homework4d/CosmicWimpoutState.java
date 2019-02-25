package com.example.homework4d;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    private Dice diceArray[] = new Dice[5];
    private String faceName;
    private ArrayList<Player> playerArrayList = new ArrayList();

    public CosmicWimpoutState(){
        whoseTurn = 1;
        currentScorePlayer1 = 0;
        currentScorePlayer2 = 0;
        currentScorePlayer3 = 0;
        //I removed the earlier dice objects and just went ahead and instanced the dice here - SL
        diceArray[0] = new Dice(1);
        diceArray[1] = new Dice(2);
        diceArray[2] = new Dice(3);
        diceArray[3] = new Dice(4);
        diceArray[4] = new Dice(5);
        for(int i = 0; i < diceArray.length; i++){
            diceArray[i].diceID = i+1;
            diceArray[i].diceState = 1;
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
        return "Player1 Score: " + currentScorePlayer1 +
                "Player2 Score: " + currentScorePlayer2 +
                "Player3 Score: " + currentScorePlayer3 +
                "Player Turn: " + whoseTurn +
                "Number of players: " + numPlayers +
                "Dice 1: " + diceArray[0].diceState +
                "Dice 2: " + diceArray[1].diceState +
                "Dice 3: " + diceArray[2].diceState +
                "Dice 4: " + diceArray[3].diceState +
                "Dice 5: " + diceArray[4].diceState;
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

    public boolean rollAllDice(int playerId){
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
    public boolean endGame(int playerId){
        if(playerId == whoseTurn) {
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }
    public boolean endTurn(int playerId) {
        if(playerId == whoseTurn) {
            if(playerId == 1){
                //add points?
                whoseTurn = 2;
            }
            else if(playerId == 2){
                //add points?
                whoseTurn = 3;
            }
            else if(playerId == 3){
                //add points?
                whoseTurn = 1;
            }
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }
    public boolean rollSingleDice(int playerId, int id ){
        if(playerId == whoseTurn) {
            diceArray[id-1].rollMe();
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }
    public String setFaces(int id){
        if(diceArray[id].diceState == 1){
            faceName = "ten";
        }
        else if(diceArray[id].diceState == 2){
            faceName = "half moons";
        }
        else if(diceArray[id].diceState == 3){
            if(id == 5) {
                faceName = "flaming sun";
            }
            else {
                faceName = "triangles";
            }
        }
        else if(diceArray[id].diceState == 4){
            faceName = "bolts";
        }
        else if(diceArray[id].diceState == 5){
            faceName = "five";
        }
        else if(diceArray[id].diceState == 6){
            faceName = "stars";
        }
        return faceName;
    }

    public int totalDiceScore(Dice[] ourDice){
        //This should check for a supernova - TOO MANY POINTS -- SL

        if(     ourDice[0].diceState == 1 &&
                ourDice[1].diceState == 1 &&
                ourDice[2].diceState == 1 &&
                ourDice[3].diceState == 1 &&
                ourDice[4].diceState == 1){
                //Some action leading to the game ending happens here
                return -1;
        }
        else if(     ourDice[0].diceState == 2 &&
                ourDice[1].diceState == 2 &&
                ourDice[2].diceState == 2 &&
                ourDice[3].diceState == 2 &&
                ourDice[4].diceState == 2){
            return 200;
        }
        else if(     ourDice[0].diceState == 3 &&
                ourDice[1].diceState == 3 &&
                ourDice[2].diceState == 3 &&
                ourDice[3].diceState == 3 &&
                ourDice[4].diceState == 3){
            return 300;
        }
        else if(     ourDice[0].diceState == 4 &&
                ourDice[1].diceState == 4 &&
                ourDice[2].diceState == 4 &&
                ourDice[3].diceState == 4 &&
                ourDice[4].diceState == 4){
            return 400;
        }
        else if(     ourDice[0].diceState == 5 &&
                ourDice[1].diceState == 5 &&
                ourDice[2].diceState == 5 &&
                ourDice[3].diceState == 5 &&
                ourDice[4].diceState == 5){
            return 500;
        }
        else if(     ourDice[0].diceState == 6 &&
                ourDice[1].diceState == 6 &&
                ourDice[2].diceState == 6 &&
                ourDice[3].diceState == 6 &&
                ourDice[4].diceState == 6){
            //Instant Winner
            //Some action leading to the game ending happens here
            return 0;
        }


        /*
        I started the logic for flashes here.
        There are 5c3 (10) combinations we have to check for. This doesn't exclude higher scoring
        rolls yet. This will probably be an else case after we handle 5-of-a-kind and 4-of-a-kind

         -- SL
        */
        //flash checking logic
        if ((     (ourDice[0].diceState == ourDice[1].diceState &&
                    ourDice[1].diceState == ourDice[2].diceState) //compare dice (0, 1, 2)
                || (ourDice[2].diceState == ourDice[3].diceState &&
                    ourDice[3].diceState == ourDice[1].diceState)//compare dice (1, 2, 3)
                || (ourDice[2].diceState == ourDice[3].diceState &&
                    ourDice[3].diceState == ourDice[4].diceState)//compare dice (2, 3, 4)
                || (ourDice[0].diceState == ourDice[1].diceState &&
                    ourDice[1].diceState == ourDice[3].diceState)//compare dice (0, 1, 3)
                || (ourDice[0].diceState == ourDice[1].diceState &&
                    ourDice[1].diceState == ourDice[4].diceState) //compare dice (0, 1, 4)
                || (ourDice[0].diceState == ourDice[2].diceState &&
                    ourDice[2].diceState == ourDice[3].diceState)//compare dice (0, 2, 3)
                || (ourDice[0].diceState == ourDice[2].diceState &&
                    ourDice[2].diceState == ourDice[4].diceState)//compare dice (0, 2, 4)
                || (ourDice[0].diceState == ourDice[3].diceState &&
                    ourDice[3].diceState == ourDice[4].diceState)//compare dice (0, 3, 4)
                || (ourDice[4].diceState == ourDice[1].diceState &&
                    ourDice[1].diceState == ourDice[3].diceState)//compare dice (1, 3, 4)
                || (ourDice[2].diceState == ourDice[1].diceState &&
                    ourDice[1].diceState == ourDice[4].diceState)//compare dice (1, 2, 4)

                )){
                    //some action making the player clear the flash happens here
                    return -1;
                }
        return 0;
    }


}
