package com.example.homework4d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button runTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTest = findViewById(R.id.runTestButton);
        runTest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        
    }
}
