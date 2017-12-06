package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RegistrationActivity extends Activity implements View.OnClickListener {

    //declare objects
    private TextView textViewEditProfile, textViewName, textViewAge, textViewLocation, textViewTennisLevel, textViewChessLevel;
    private EditText editTextName,editTextAge, editTextLocation;
    private Spinner spinnerTennisLevel, spinnerChessLevel;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //link objects
        //textViewEditProfile = (TextView) findViewById(R.id.textViewEditProfile);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAge = (TextView) findViewById(R.id.textViewAge);
        textViewLocation = (TextView) findViewById(R.id.textViewLocation);
        textViewTennisLevel = (TextView) findViewById(R.id.textViewTennisLevel);
        textViewChessLevel = (TextView) findViewById(R.id.textViewChessLevel);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        spinnerTennisLevel = (Spinner) findViewById(R.id.spinnerTennisLevel);
        spinnerChessLevel = (Spinner) findViewById(R.id.spinnerChessLevel);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        //set up button listener
        buttonSubmit.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        //Initializing Firebase database
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference profileRef = db.getReference("Profiles");

        if (view == buttonSubmit) {
            //get email address
            Intent intent = getIntent();
            String profileEmailAddress = intent.getStringExtra("Username");
            String profileName = editTextName.getText().toString();
            String profileAge = editTextAge.getText().toString();
            String profileLocation = editTextLocation.getText().toString();
            String profileTennisLevel = spinnerTennisLevel.getSelectedItem().toString();
            String profileChessLevel = spinnerChessLevel.getSelectedItem().toString();
            double profileLatitude = 0, profileLongitude = 0;

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;

            try {
                addresses = geocoder.getFromLocationName(profileLocation, 1);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Invalid Address", Toast.LENGTH_SHORT).show();
            }
            Address address = addresses.get(0);
            if(addresses.size() > 0) {
                profileLatitude = addresses.get(0).getLatitude();
                profileLongitude = addresses.get(0).getLongitude();
            }

            ProfileClass myProfile = new ProfileClass(profileEmailAddress, profileName, profileAge, profileLocation, profileTennisLevel, profileChessLevel, profileLatitude, profileLongitude);
            profileRef.push().setValue(myProfile);

            Intent intentHome = new Intent(this,HomepageActivity.class);
            intentHome.putExtra("Username", profileEmailAddress);
            this.startActivity(intentHome);
        }
    }
}
