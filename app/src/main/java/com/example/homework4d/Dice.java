package com.example.homework4d;

import java.lang.Math;

public class Dice {

    int diceState;
    boolean isBlackDice;



    public void rollMe(){
        this.diceState = (int)(Math.random()*6);
    }



}
