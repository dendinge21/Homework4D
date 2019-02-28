package com.example.homework4d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button runTest;
    EditText edit;
    //CosmicWimpoutState state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTest = findViewById(R.id.runTestButton);
        runTest.setOnClickListener(this);

        edit = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        edit.setText("");
        CosmicWimpoutState firstInstance = new CosmicWimpoutState();
        firstInstance.rollAllDice(1);
        edit.append("Player one has rolled, " + firstInstance.getDiceState(0) + ", "
            + firstInstance.getDiceState(1) + ", " + firstInstance.getDiceState(2) +
                ", " + firstInstance.getDiceState(3) + ", and  " +
                firstInstance.getDiceState(4) + "\n");
        int whoseTurnBefore = firstInstance.getWhoseTurn();
        firstInstance.endTurn(1);
        int whoseTurn = firstInstance.getWhoseTurn();
        edit.append("Player " + whoseTurnBefore + " has ended their turn, it is now" +
                " Player " + whoseTurn +" turn\n");
        //firstInstance.rollSingleDice(1,2);
        //append text
        firstInstance.endGame(2);
        edit.append("Player " + whoseTurn + " ended the game.\n");
        CosmicWimpoutState secondInstance = new CosmicWimpoutState(firstInstance);
        CosmicWimpoutState thirdInstance = new CosmicWimpoutState(secondInstance);
        CosmicWimpoutState fourthInstance = new CosmicWimpoutState(thirdInstance);
        edit.append("Second Instance: " + secondInstance.toString() + " ");
        edit.append("Fourth Instance: " + fourthInstance.toString() + " ");



    }
}
