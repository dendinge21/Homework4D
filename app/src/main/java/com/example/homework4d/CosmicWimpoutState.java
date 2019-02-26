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

    private int whoseTurn;
    private int numPlayers;
    private int turnScore = 0;
    private boolean canReRoll = true;
    private boolean haveToReRoll = false;
    private Dice diceArray[] = new Dice[5];
    private String faceName;
    private ArrayList<Player> playerArrayList = new ArrayList();

    private int halfMoonReRoll;
    private int triangleReRoll;
    private int boltReRoll;
    private int fiveReRoll;
    private int starReRoll;
    private int tenReRoll;

    public CosmicWimpoutState(){
        whoseTurn = 1;
        numPlayers = 3;
        //for loop creates dice in all diceArray indices.
        for(int i = 0; i<5; i++){
            diceArray[i] = new Dice(i);
        }
        playerArrayList.add(new Player(1));
        playerArrayList.add(new Player(2));
        playerArrayList.add(new Player(3));
        for(int i = 0; i < diceArray.length; i++){
            //initialize all dice
            diceArray[i].diceID = i+1;
            diceArray[i].diceState = 1;
        }
        for(int i = 0; i<playerArrayList.size();i++){
            //player instance variables
            playerArrayList.get(i).setPlayerID(i+1);
            playerArrayList.get(i).setPlayerScore(0);
        }

        //finish once more instance variables

    }

    //implement a deep copy
    public CosmicWimpoutState(CosmicWimpoutState orig){

        whoseTurn = orig.whoseTurn;

        Dice copyArray[] = new Dice[5];
        for(int i =0; i < diceArray.length; i++){
            copyArray[i] = new Dice(i+1);
            copyArray[i].diceID = orig.diceArray[i].diceID;
            copyArray[i].diceState = orig.diceArray[i].diceState;
        }

        ArrayList<Player> copyPlayerArrayList = new ArrayList();
        for(int i=0; i <orig.playerArrayList.size();i++){
            copyPlayerArrayList.add(new Player(i+1));
            copyPlayerArrayList.get(i).setPlayerID(orig.playerArrayList.get(i).getPlayerID());
            copyPlayerArrayList.get(i).setPlayerScore(orig.playerArrayList.get(i).getPlayerScore());
        }

    }

    /**
    *toString(); describes the state of a game as a string, prints all the values
     * of all the variables; add @Override tag
    */

    @Override
    public String toString() {
        return "Player1 Score: " + playerArrayList.get(0).getPlayerScore() +
                "Player2 Score: " + playerArrayList.get(1).getPlayerScore() +
                "Player3 Score: " + playerArrayList.get(2).getPlayerScore() +
                "Player Turn: " + whoseTurn  +
                "Number of Players" + numPlayers +
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
            if(totalDiceScore(diceArray,playerId) != -1) {
                turnScore = turnScore + totalDiceScore(diceArray,playerId);
            }
            else{
                turnScore = 0;
            }
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
            int currentScore = playerArrayList.get(playerId-1).getPlayerScore();
            if(playerId == 1){
                playerArrayList.get(playerId - 1).setPlayerScore(currentScore + turnScore);
                whoseTurn = 2;
                turnScore = 0;
            }
            else if(playerId == 2){
                playerArrayList.get(playerId - 1).setPlayerScore(currentScore + turnScore);
                whoseTurn = 3;
                turnScore = 0;
            }
            else if(playerId == 3){
                playerArrayList.get(playerId - 1).setPlayerScore(currentScore + turnScore);
                whoseTurn = 1;
                turnScore = 0; //reset turnScore to 0
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

    public int totalDiceScore(Dice[] ourDice, int playerId){
        //SUPERNOVA AND FREIGHT TRAIN CHECKING
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
            haveToReRoll = true;
            return 200;
        }

        else if(     ourDice[0].diceState == 4 &&
                ourDice[1].diceState == 4 &&
                ourDice[2].diceState == 4 &&
                ourDice[3].diceState == 4 &&
                ourDice[4].diceState == 4){
            //turnScore = turnScore + 400;
            haveToReRoll = true;
            return 400;
        }
        else if(     ourDice[0].diceState == 5 &&
                ourDice[1].diceState == 5 &&
                ourDice[2].diceState == 5 &&
                ourDice[3].diceState == 5 &&
                ourDice[4].diceState == 5){
            haveToReRoll = true;
           // turnScore = turnScore + 500;
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
        //END SUPERNOVA AND FREIGHT TRAIN CHECKING


        int halfMoonCount = 0;
        int triangleCount=0;
        int boltCount=0;
        int fiveCount =0;
        int starCount=0;
        int tenCount=0;


        for(int i =0; i < ourDice.length; i++){
            if(ourDice[i].getDiceState() == 1){
                tenCount++;
                if(tenCount == 4){
                    tenReRoll=i;
                }
            }
            if(ourDice[i].getDiceState() == 2){
                halfMoonCount++;
                if(halfMoonCount == 4){
                    halfMoonReRoll=i;
                }
            }
            if(ourDice[i].getDiceState() == 3 && ourDice[i].diceID != 5){
                triangleCount++;
                if(triangleCount == 4){
                    triangleReRoll=i;
                }
            }
            if(ourDice[i].getDiceState() == 4){
                boltCount++;
                if(boltCount == 4){
                    boltReRoll=i;
                }
            }
            if(ourDice[i].getDiceState() == 5){
                fiveCount++;
                if(fiveCount == 4){
                    fiveReRoll=i;
                }
            }
            if(ourDice[i].getDiceState() == 6){
                starCount++;
                if(starCount == 4){
                    starReRoll=i;
                }
            }
        }
        //END DICE COUNTING


        //BEGIN FLAMING SUN FLASH CASES
        if(halfMoonCount == 2 && ourDice[5].diceState == 3){
            //turnScore = turnScore + 20;
            if(halfMoonCount ==4){
                rollSingleDice(playerId, (halfMoonReRoll + 1));
                haveToReRoll = true;
            }
            return 20;
        }
        if(triangleCount == 2 && ourDice[5].diceState == 3){
            //turnScore = turnScore + 30;
            if(triangleCount ==4){
                rollSingleDice(playerId, (triangleReRoll + 1));
                haveToReRoll = true;
            }
            return 30;
        }
        if(boltCount == 2 && ourDice[5].diceState == 3){
            //turnScore = turnScore + 40;
            if(boltCount ==4){
                rollSingleDice(playerId, (boltReRoll + 1));
                haveToReRoll = true;
            }
            return 40;
        }
        if(fiveCount == 2 && ourDice[5].diceState == 3){
            //  turnScore = turnScore + 50;
            if(fiveCount ==4){
                rollSingleDice(playerId, (fiveReRoll + 1));
                haveToReRoll = true;
            }
            return 50;
        }
        if(starCount == 2 && ourDice[5].diceState == 3){
            //  turnScore = turnScore + 60;
            if(starCount ==4){
                rollSingleDice(playerId, (starReRoll + 1));
                haveToReRoll = true;
            }
            return 60;
        }
        if(tenCount == 2 && ourDice[5].diceState == 3){
            // turnScore = turnScore + 100;
            if(tenCount ==4){
                rollSingleDice(playerId, (tenReRoll + 1));
                haveToReRoll = true;
            }
            return 100;
        }//END FLAMING SUN FLASH CASE HANDLING



        //BEGIN NORMAL FLASH HANDLING
        if(halfMoonCount >= 3 && halfMoonCount < 5){
            //turnScore = turnScore + 20;
            if(halfMoonCount ==4){
                rollSingleDice(playerId, (halfMoonReRoll + 1));
                rollSingleDice(playerId, (halfMoonReRoll +2));
                haveToReRoll = true;
            }
            return 20;
        }
        if(triangleCount >= 3 && triangleCount < 5){
            //turnScore = turnScore + 30;
            if(triangleCount ==4){
                rollSingleDice(playerId, (triangleReRoll + 1));
                haveToReRoll = true;
            }
            return 30;
        }
        if(boltCount >= 3 && boltCount < 5){
            //turnScore = turnScore + 40;
            if(boltCount ==4){
                rollSingleDice(playerId, (boltReRoll + 1));
                rollSingleDice(playerId, (boltReRoll +2));
                haveToReRoll = true;
            }
            return 40;
        }
        if(fiveCount >= 3 && fiveCount < 5){
          //  turnScore = turnScore + 50;
            if(fiveCount ==4){
                rollSingleDice(playerId, (fiveReRoll + 1));
                rollSingleDice(playerId, (fiveReRoll +2));
                haveToReRoll = true;
            }
            return 50;
        }
        if(starCount >= 3 && starCount < 5){
          //  turnScore = turnScore + 60;
            if(starCount ==4){
                rollSingleDice(playerId, (starReRoll + 1));
                rollSingleDice(playerId, (starReRoll +2));
                haveToReRoll = true;
            }
            return 60;
        }
        if(tenCount >= 3 && tenCount < 5){
           // turnScore = turnScore + 100;
            if(tenCount ==4){
                rollSingleDice(playerId, (tenReRoll + 1));
                rollSingleDice(playerId, (tenReRoll +2));
                haveToReRoll = true;
            }
            return 100;
        }
        //END NORMAL FLASH HANDLING


        //BEGIN 10 & 5 COUNTING CASES
        if(tenCount != 0){
            if(fiveCount != 0){
                return (fiveCount*5) + (tenCount*10);
                //I don't know if we need to set haveToReroll to true here --SL
            }
            return tenCount*10;
        }
        if(fiveCount != 0){
            return fiveCount*5;
            //I don't know if we need to set haveToReroll to true here --SL
        }
        //END 10 & 5 COUNTING CASES

        //BEGIN WIMPOUT CASE
        //***** ONE OF YOU MIGHT WANT TO DOUBLE CHECK THIS *****
        //first make sure there are no dice that can score on their own, include flaming sun
        if(fiveCount ==0 && tenCount == 0 && ourDice[5].diceState != 3) {
            //iterate through the dice and compare them to check for duplicate values
            topLoop:
            for (int i = 0; i < 5; i++) {
                for (int k = 0; k < 5; k++) {
                    //this avoids comparing the dice with itself
                    if (i != k) {
                        if (ourDice[i].diceState == ourDice[k].diceState) {
                            //Found two dice that are similar, thus no wimpout
                            //break all loops and return a value that ends the turn
                            break topLoop;
                        }
                    }
                }
            }
            return -1;
        }
        //END WIMPOUT CASE



        return 0;
    }



}
