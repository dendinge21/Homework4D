package com.example.homework4d;

/**
 * Dice allows us to access the state of the dice after it has been rolled.
 * as well as retrieve which dice we are looking at
 *
 * @Authors: Olivia Dendinger, Sam Lemly, David Campbell, and Kayla Moore
 */
public class Dice {

    protected int diceState;
    protected int diceID;


    public Dice(int ID){
        this.diceID = ID;
        diceState = 1;
    }

    public int getDiceState(){
        return diceState;
    }
    public void setDiceID(int newid){
        this.diceID = newid;

    }

    public void rollMe(){
        this.diceState = (int)(Math.random()*6 + 1);
    }

    public String getValAsString(){
        switch (this.diceState) {
            case 1:
                return "Tens";
            case 2:
                return "Moons";
            case 3:
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
