package com.example.homework4d;

/**
 * MainActivity
 *
 * This class is our MainActivity where each instance of the GameState will
 * be created.
 *
 * @Authors: Olivia Dendinger, Sam Lemly, David Campbell, and Kayla Moore
 *
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declaring Views
    Button runTest;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing runTest and edit
        runTest = findViewById(R.id.runTestButton);
        runTest.setOnClickListener(this);

        edit = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        edit.setText(""); // clear any text user entered before

        //create an instance of the game state and copy it
        CosmicWimpoutState firstInstance = new CosmicWimpoutState();
        CosmicWimpoutState secondInstance = new CosmicWimpoutState(firstInstance);

        int whoRolled = firstInstance.getWhoseTurn(); //whose turn is it


        //roll 3 individual die to test that the rollSingleDie() method works
        firstInstance.rollSingleDie(whoRolled,2);
        firstInstance.rollSingleDie(whoRolled,4);
        firstInstance.rollSingleDie(whoRolled,1);


        //append text to show what they rolled
        edit.append("Player " + whoRolled + " has rolled, " + firstInstance.getDiceVal(0) + ", "
                + firstInstance.getDiceVal(1) + " and "
                + firstInstance.getDiceVal(3) + "\n");


        //get whose turn it is now cause they could of wimped out
        int nowWhoRolls = firstInstance.getWhoseTurn();
        //that player rolls all the die
        firstInstance.rollAllDice(nowWhoRolls);
        //edit text to show what they rolled
        edit.append("Player " + nowWhoRolls + " has rolled, " + firstInstance.getDiceVal(0) + ", "
            + firstInstance.getDiceVal(1) + ", " + firstInstance.getDiceVal(2) +
                ", " + firstInstance.getDiceVal(3) + ", and  " +
                firstInstance.getDiceVal(4) + "\n");

        //get whose turn it is
        int whoseTurnBefore = firstInstance.getWhoseTurn();

        //that player ends the turn
        firstInstance.endTurn(whoseTurnBefore);

        //get whose turn it is after they end it
        int whoseTurn = firstInstance.getWhoseTurn();

        //append text to show whose turn it is now
        edit.append("Player " + whoseTurnBefore + " has ended their turn, it is now" +
                " Player " + whoseTurn +" turn\n");

        //player ends game
        firstInstance.endGame(2);
        edit.append("Player " + whoseTurn + " ended the game.\n");

        //create another instance of the game state and another copy
        CosmicWimpoutState thirdInstance = new CosmicWimpoutState();
        CosmicWimpoutState fourthInstance = new CosmicWimpoutState(thirdInstance);
        
        //show that the copies are the same
        edit.append("Second Instance: " + secondInstance.toString() + " ");
        edit.append("Fourth Instance: " + fourthInstance.toString() + " ");
    }
}
