package com.example.homework4d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button runTest;
    EditText edit;

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
        CosmicWimpoutState secondInstance = new CosmicWimpoutState(firstInstance);
        //firstInstance.rollAllDice(1);
        //append text
        //firstInstance.rollSingleDice(1,2);
        //append text
        //firstInstance.endTurn(1);
        //append text
        //firstInstance.endGame(2);
        //edit the multi line text (append method)
        CosmicWimpoutState thirdInstance = new CosmicWimpoutState();
        CosmicWimpoutState fourthInstance = new CosmicWimpoutState(thirdInstance);
        edit.append("Second Instance: " + secondInstance.toString() + " ");
        edit.append("Fourth Instance: " + fourthInstance.toString() + " ");



    }
}
