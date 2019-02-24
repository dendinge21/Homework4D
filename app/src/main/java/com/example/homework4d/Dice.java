package com.example.homework4d;


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
        this.diceState = (int)(Math.random()*6);
    }



}
