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
        CosmicWimpoutState firstInstance = new CosmicWimpoutState(0,0,0,1);
        CosmicWimpoutState secondInstance = new CosmicWimpoutState(firstInstance);
        //call methods on first instance
        //edit the multi line text



    }
}
