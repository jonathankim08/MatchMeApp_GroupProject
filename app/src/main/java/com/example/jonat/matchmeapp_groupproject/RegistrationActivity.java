package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegistrationActivity extends Activity implements View.OnClickListener {

    //declare objects
    private TextView textViewEditProfile, textViewName, textViewLocation, textViewTennisLevel, textViewChessLevel;
    private EditText editTextName,editTextLocation;
    private Spinner spinnerTennisLevel, spinnerChessLevel;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //link objects
        textViewEditProfile = (TextView) findViewById(R.id.textViewEditProfile);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewLocation = (TextView) findViewById(R.id.textViewLocation);
        textViewTennisLevel = (TextView) findViewById(R.id.textViewTennisLevel);
        textViewChessLevel = (TextView) findViewById(R.id.textViewChessLevel);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        spinnerTennisLevel = (Spinner) findViewById(R.id.spinnerTennisLevel);
        spinnerChessLevel = (Spinner) findViewById(R.id.spinnerChessLevel);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        //set up button listener
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
