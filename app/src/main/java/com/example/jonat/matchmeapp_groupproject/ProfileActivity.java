package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    //declare objects
    private TextView textViewName, textViewAge, textViewLocation, textViewTennisLevel, textViewChessLevel;
    private TextView textViewNameValue, textViewAgeValue, textViewLocationValue, textViewTennisLevelValue, textViewChessLevelValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //link objects
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        textViewLocation = (TextView) findViewById(R.id.textViewLocation);
        textViewTennisLevel = (TextView) findViewById(R.id.textViewTennisLevel);
        textViewChessLevel = (TextView) findViewById(R.id.textViewChessLevel);
        textViewNameValue = (TextView) findViewById(R.id.textViewNameValue);
        textViewAgeValue = (TextView) findViewById(R.id.textViewAgeValue);
        textViewLocationValue = (TextView) findViewById(R.id.textViewLocationValue);
        textViewTennisLevelValue = (TextView) findViewById(R.id.textViewTennisLevelValue);
        textViewChessLevelValue = (TextView) findViewById(R.id.textViewChessLevelValue);

    }
}