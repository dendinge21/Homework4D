package com.example.homework4d;

import java.lang.Math;

public class Dice {

    private int diceState;
    private int diceID;


    public Dice(int ID){
        this.diceID = ID;
    }

    public void rollMe(){
        this.diceState = (int)(Math.random()*6);
    }


    public int getDiceState(){
        return diceState;
    }
    public void setDiceID(int newid{
        this.diceID = newid;

    }


}
