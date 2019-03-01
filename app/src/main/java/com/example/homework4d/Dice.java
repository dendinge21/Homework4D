package com.example.homework4d;

/**
 * Dice
 *
 * Dice allows us to access the state of the dice after it has been rolled.
 * as well as retrieve which dice we are looking at.
 *
 * @Authors: Olivia Dendinger, Sam Lemly, David Campbell, and Kayla Moore
 */
public class Dice {

    //initializing variables
    protected int dieState;
    protected int dieID;

    /**
     * Dice constructor
     * @param ID
     */
    public Dice(int ID){
        this.dieID = ID;
        dieState = 1;
    }

    /**
     * getDiceState method gets the current state of the die
     * @return diceState
     */
    public int getDieState(){
        return dieState;
    }

    /**
     * setDiceID sets the ID of the current die
     * @param newid
     */
    public void setDieID(int newid){
        this.dieID = newid;
    }

    /**
     * rollMe rolls the current die
     */
    public void rollMe(){
        this.dieState = (int)(Math.random()*6 + 1);
    }

    /**
     * getValAsString returns the name of each respective
     * side of the current die.
     * @return
     */
    public String getValAsString(){
        switch (this.dieState) {
            case 1:
                return "Tens";
            case 2:
                return "Moons";
            case 3:
                if(this.dieID == 3)
                    return "Flaming Sun";
                else
                    return "Triangles";
            case 4:
                return "Bolts";
            case 5:
                return "Fives";
            case 6 :
                return "Stars";
        }
        return "null";
    }
}
