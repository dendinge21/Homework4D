package com.example.homework4d;

/**
 * CosmicWimpoutState
 *
 * This class will be our local game state. It initializes the default
 * game state as well can be called to make a copy of the current game state
 *
 * @Authors: Olivia Dendinger, Sam Lemly, David Campbell, and Kayla Moore
 *
 */

import java.util.ArrayList;

public class CosmicWimpoutState {
    //instance variables that encompass all needed information

    //to satisfy Serializable interface
    private static final long serialVersionUID = 12345678910L;

    //initializing game variables
    private int whoseTurn;
    private int numPlayers;
    private int turnScore = 0;
    private boolean canReRoll = true;
    private boolean haveToReRoll = false;
    private Dice diceArray[] = new Dice[5];
    private ArrayList<Player> playerArrayList = new ArrayList();

    //initializing dice re-roll variables
    private int halfMoonReRoll;
    private int triangleReRoll;
    private int boltReRoll;
    private int fiveReRoll;
    private int starReRoll;
    private int tenReRoll;

    /**
     * CosmicWimpoutState default constructor
     * In the test iteration, this initializes three players
     */
    public CosmicWimpoutState(){
        whoseTurn = 1;
        numPlayers = 3;
        //for loop creates dice in all diceArray indices.
        for(int i = 0; i < 5; i++){
            diceArray[i] = new Dice(i);
        }
        this.playerArrayList.add(new Player(1));
        this.playerArrayList.add(new Player(2));
        this.playerArrayList.add(new Player(3));

        for(int i = 0; i < diceArray.length; i++){
            //initialize all dice
            this.diceArray[i].dieID = i+1;
            this.diceArray[i].dieState = 1;
        }
        for(int i = 0; i < playerArrayList.size();i++){
            //player instance variables
            this.playerArrayList.get(i).setPlayerID(i+1);
            this.playerArrayList.get(i).setPlayerScore(0);
        }
    }

    /**
     * CosmicWimpoutState constructor that creates a deep copy
     * @param orig
     */
    public CosmicWimpoutState(CosmicWimpoutState orig){

        whoseTurn = orig.whoseTurn;

        for(int i =0; i < diceArray.length; i++){
            this.diceArray[i] = new Dice(i+1);
            this.diceArray[i].dieID = orig.diceArray[i].dieID;
            this.diceArray[i].dieState = orig.diceArray[i].dieState;
        }

        for(int i=0; i < orig.playerArrayList.size();i++){
            this.playerArrayList.add(new Player(i+1));
            this.playerArrayList.get(i).setPlayerID(orig.playerArrayList.get(i).getPlayerID());
            this.playerArrayList.get(i).setPlayerScore(orig.playerArrayList.get(i).getPlayerScore());
        }
    }

    /**
     * toString prints the values of all of the variables in the game state
     * @return the converted string
     */
    @Override
    public String toString() {

        return "Player Turn: " + this.whoseTurn  + "\n" +
                " Number of Players " + this.numPlayers  + "\n" +
                " Dice 1: " + diceArray[0].getValAsString() + "\n" +
                " Dice 2: " + diceArray[1].getValAsString() + "\n" +
                " Dice 3: " + diceArray[2].getValAsString() + "\n" +
                " Dice 4: " + diceArray[3].getValAsString() + "\n" +
                " Dice 5: " + diceArray[4].getValAsString() + "\n" +
                " Player1 Score: " + playerArrayList.get(0).getPlayerScore() + "\n" +
                " Player2 Score: " + playerArrayList.get(1).getPlayerScore() + "\n" +
                " Player3 Score: " + playerArrayList.get(2).getPlayerScore() + "\n" ;
    }

    /**
     * Rolls all five dice at once
     * @param playerId
     * @return return true if legal move
     */
    public boolean rollAllDice(int playerId){
        if(playerId == whoseTurn) {
            //rolls all dice giving each a value 1-6
            for(int i = 0; i < 5; i++) {
                this.diceArray[i].dieState = (int) (Math.random() * 6 + 1);
            }
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

    /**
     * endGame - will quit the game and return back to main menu
     * @param playerId
     * @return true if legal move
     */
    public boolean endGame(int playerId){
        if(playerId == whoseTurn) {
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }

    /**
     * endTurn - if player chooses to end turn, add up their turn score to their
     * overall game score, switch to next player
     * @param playerId
     * @return true if legal move
     */
    public boolean endTurn(int playerId) {
        if(playerId == whoseTurn) {
            int currentScore = playerArrayList.get(playerId-1).getPlayerScore();
            if(playerId == 1){
                playerArrayList.get(playerId - 1).setPlayerScore(currentScore + turnScore);
                whoseTurn = 2;
                turnScore = 0; //reset turn score to 0 for next player
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

    /**
     * Rolls only a single dice
     * Can only be called once the player knows what the previous roll was
     * @param playerId
     * @param id - which dice the player wants to roll
     * @return true if valid
     */
    public boolean rollSingleDie(int playerId, int id ){
        if(playerId == whoseTurn) {
            diceArray[id-1].rollMe();
            if(totalDiceScore(diceArray,playerId) != -1) {
                turnScore = turnScore + totalDiceScore(diceArray,playerId);
            }
            else{
                turnScore = 0;
                this.endTurn(playerId);

            }
            return true;
        }
        else{
            // illegal move
            return false;
        }
    }

    /**
     * Totals the scores of all possible rolls, as well has reroll for the
     * player when they are required.
     *
     * @param ourDice
     * @param playerId
     * @returns an int
     */
    public int totalDiceScore(Dice[] ourDice, int playerId){
        //SUPERNOVA AND FREIGHT TRAIN CHECKING
        if(     ourDice[0].dieState == 1 &&
                ourDice[1].dieState == 1 &&
                ourDice[2].dieState == 1 &&
                ourDice[3].dieState == 1 &&
                ourDice[4].dieState == 1){
                //Some action leading to the game ending happens here
                return -1;
        }
        else if(ourDice[0].dieState == 2 &&
                ourDice[1].dieState == 2 &&
                ourDice[2].dieState == 2 &&
                ourDice[3].dieState == 2 &&
                ourDice[4].dieState == 2){
            haveToReRoll = true;
            return 200;
        }
        else if(ourDice[0].dieState == 4 &&
                ourDice[1].dieState == 4 &&
                ourDice[2].dieState == 4 &&
                ourDice[3].dieState == 4 &&
                ourDice[4].dieState == 4){
            //turnScore = turnScore + 400;
            haveToReRoll = true;
            return 400;
        }
        else if(ourDice[0].dieState == 5 &&
                ourDice[1].dieState == 5 &&
                ourDice[2].dieState == 5 &&
                ourDice[3].dieState == 5 &&
                ourDice[4].dieState == 5){
            haveToReRoll = true;
           // turnScore = turnScore + 500;
            return 500;
        }
        else if(ourDice[0].dieState == 6 &&
                ourDice[1].dieState == 6 &&
                ourDice[2].dieState == 6 &&
                ourDice[3].dieState == 6 &&
                ourDice[4].dieState == 6){
            //Instant Winner
            //Some action leading to the game ending happens here
            return 0;
        }
        //END SUPERNOVA AND FREIGHT TRAIN CHECKING

        //count variables for the dice
        int halfMoonCount = 0;
        int triangleCount=0;
        int boltCount=0;
        int fiveCount =0;
        int starCount=0;
        int tenCount=0;

        //BEGIN DICE COUNTING
        for(int i =0; i < ourDice.length; i++){
            if(ourDice[i].getDieState() == 1){
                tenCount++;
                if(tenCount == 4){
                    tenReRoll=i;
                }
                else if(tenCount == 2){
                    tenReRoll = i+1;
                }
            }
            if(ourDice[i].getDieState() == 2){
                halfMoonCount++;
                if(halfMoonCount == 4){
                    halfMoonReRoll=i;
                }
                else if(halfMoonCount == 2){
                    halfMoonReRoll = i+1;
                }
            }
            if(ourDice[i].getDieState() == 3 && ourDice[i].dieID != 5){
                triangleCount++;
                if(triangleCount == 4){
                    triangleReRoll=i;
                }
                else if(triangleCount == 2){
                    triangleReRoll = i+1;
                }
            }
            if(ourDice[i].getDieState() == 4){
                boltCount++;
                if(boltCount == 4){
                    boltReRoll=i;
                }
                else if(boltCount == 2){
                    boltReRoll = i+1;
                }
            }
            if(ourDice[i].getDieState() == 5){
                fiveCount++;
                if(fiveCount == 4){
                    fiveReRoll=i;
                }
                else if(fiveCount == 2){
                    fiveReRoll = i+1;
                }
            }
            if(ourDice[i].getDieState() == 6){
                starCount++;
                if(starCount == 4){
                    starReRoll=i;
                }
                else if(starCount == 2){
                    starReRoll = i+1;
                }
            }
        }
        //END DICE COUNTING


        //BEGIN FLAMING SUN FLASH CASES
        if(tenCount == 2 && ourDice[4].dieState == 3){
            return 100;
        }
        else if(starCount == 2 && ourDice[4].dieState == 3){
            return 60;
        }
        else if(fiveCount == 2 && ourDice[4].dieState == 3){
            return 50;
        }
        else if(boltCount == 2 && ourDice[4].dieState == 3){
            return 40;
        }
        else if(triangleCount == 2 && ourDice[4].dieState == 3){
            return 30;
        }
        else if(halfMoonCount == 2 && ourDice[4].dieState == 3){
            return 20;
        }
        //END FLAMING SUN FLASH CASE HANDLING


        //BEGIN NORMAL FLASH HANDLING
        if(halfMoonCount >= 3 && halfMoonCount < 5){
            if(halfMoonCount == 4) {
                rollSingleDie(playerId, (halfMoonReRoll + 1));
                if(halfMoonReRoll == 5){
                    rollSingleDie(playerId, 1);
                }
                else{
                    rollSingleDie(playerId, halfMoonReRoll +2);
                }
                haveToReRoll = true;
            }
            return 20;
        }
        if(triangleCount >= 3 && triangleCount < 5){
            if(triangleCount == 4) {
                rollSingleDie(playerId, (triangleReRoll + 1));
                haveToReRoll = true;
            }
            return 30;
        }
        if(boltCount >= 3 && boltCount < 5){
            if(boltCount == 4){
                rollSingleDie(playerId, (boltReRoll + 1));
                if(boltReRoll == 5){
                    rollSingleDie(playerId, 1);
                }
                else{
                    rollSingleDie(playerId, boltReRoll +2);
                }
                haveToReRoll = true;
            }
            return 40;
        }
        if(fiveCount >= 3 && fiveCount < 5){
          //  turnScore = turnScore + 50;
            if(fiveCount == 4){
                rollSingleDie(playerId, (fiveReRoll + 1));
                if(fiveReRoll == 5){
                    rollSingleDie(playerId, 1);
                }
                else{
                    rollSingleDie(playerId, fiveReRoll +2);
                }
                haveToReRoll = true;
            }
            return 50;
        }
        if(starCount >= 3 && starCount < 5){
          //  turnScore = turnScore + 60;
            if(starCount == 4){
                rollSingleDie(playerId, (starReRoll + 1));
                if(starReRoll == 5){
                    rollSingleDie(playerId, 1);
                }
                else{
                    rollSingleDie(playerId, starReRoll +2);
                }
                haveToReRoll = true;
            }
            return 60;
        }
        if(tenCount >= 3 && tenCount < 5){
           // turnScore = turnScore + 100;
            if(tenCount == 4){
                rollSingleDie(playerId, (tenReRoll + 1));
                if(tenReRoll == 5){
                    rollSingleDie(playerId, 1);
                }
                else{
                    rollSingleDie(playerId, tenReRoll +2);
                }
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

        //BEGIN ONLY FLAMING SUN CASE
        if(tenCount == 0 && fiveCount == 0 && ourDice[4].dieState == 3){
            return 10;
        }
        //END ONLY FLAMING SUN CASE

        //BEGIN WIMPOUT CASE
        if(fiveCount == 0 && tenCount == 0 && ourDice[4].dieState != 3) {
            return -1;
        }
        //END WIMPOUT CASE

        return 0;
    }

    public String getDiceVal(int diceId){
       return this.diceArray[diceId].getValAsString();
    }

    public int getWhoseTurn(){
        return this.whoseTurn;
    }
}