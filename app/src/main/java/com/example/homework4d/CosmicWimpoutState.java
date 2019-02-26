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

    public CosmicWimpoutState(){
        whoseTurn = 1;
        numPlayers = 3;
        //I removed the earlier dice objects and just went ahead and instanced the dice here - SL
        diceArray[0] = new Dice(1);
        diceArray[1] = new Dice(2);
        diceArray[2] = new Dice(3);
        diceArray[3] = new Dice(4);
        diceArray[4] = new Dice(5);
        playerArrayList.add(new Player(1));
        playerArrayList.add(new Player(2));
        playerArrayList.add(new Player(3));
        for(int i = 0; i < diceArray.length; i++){
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
            turnScore = turnScore + 200;
            return 200;
        }

        else if(     ourDice[0].diceState == 4 &&
                ourDice[1].diceState == 4 &&
                ourDice[2].diceState == 4 &&
                ourDice[3].diceState == 4 &&
                ourDice[4].diceState == 4){
            turnScore = turnScore + 400;
            return 400;
        }
        else if(     ourDice[0].diceState == 5 &&
                ourDice[1].diceState == 5 &&
                ourDice[2].diceState == 5 &&
                ourDice[3].diceState == 5 &&
                ourDice[4].diceState == 5){
            turnScore = turnScore + 500;
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
                } */

        int halfMoonCount = 0;
        int triangleCount=0;
        int boltCount=0;
        int fiveCount =0;
        int starCount=0;
        int tenCount=0;
        for(int i =0; i < ourDice.length; i++){
            if(ourDice[i].getDiceState() == 1){
                tenCount++;
            }
            if(ourDice[i].getDiceState() == 2){
                halfMoonCount++;
            }
            if(ourDice[i].getDiceState() == 3 && ourDice[i].diceID != 5){
                triangleCount++;
            }
            if(ourDice[i].getDiceState() == 4){
                tenCount++;
            }
            if(ourDice[i].getDiceState() == 5){
                fiveCount++;
            }
            if(ourDice[i].getDiceState() == 6){
                starCount++;
            }
        }
        if(halfMoonCount >= 3 && halfMoonCount < 5){
            turnScore = turnScore + 20;
            if(halfMoonCount ==4){
                haveToReRoll = true;
            }
            return 20;
        }
        if(triangleCount >= 3 && triangleCount < 5){
            turnScore = turnScore + 30;
            if(triangleCount ==4){
                haveToReRoll = true;
            }
            return 30;
        }
        if(boltCount >= 3 && boltCount < 5){
            turnScore = turnScore + 40;
            if(boltCount ==4){
                haveToReRoll = true;
            }
            return 40;
        }
        if(fiveCount >= 3 && fiveCount < 5){
            turnScore = turnScore + 50;
            if(fiveCount ==4){
                haveToReRoll = true;
            }
            return 50;
        }
        if(starCount >= 3 && starCount < 5){
            turnScore = turnScore + 60;
            if(starCount ==4){
                haveToReRoll = true;
            }
            return 60;
        }
        if(tenCount >= 3 && tenCount < 5){
            turnScore = turnScore + 100;
            if(tenCount ==4){
                haveToReRoll = true;
            }
            return 100;
        }
        return 0;
    }



}
