package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtherUsersProfiles extends Activity implements View.OnClickListener {

    private TextView textViewName, textViewAge, textViewLocation, textViewTennisLevel, textViewChessLevel;
    private TextView textViewNameValue, textViewAgeValue, textViewLocationValue, textViewTennisLevelValue, textViewChessLevelValue;
    private Button SendChat;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_users_profiles);

        Intent intent = getIntent();
        final String otherProfileId = intent.getStringExtra("otherProfileId");

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
        SendChat = (Button) findViewById(R.id.btnSendChat);

        SendChat.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference profileRef = db.getReference("Profiles");

        profileRef.child(otherProfileId).limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profileRef.child(otherProfileId).limitToLast(1).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ProfileClass findProfile = new ProfileClass();
                        findProfile = dataSnapshot.getValue(ProfileClass.class);

                        textViewNameValue.setText(findProfile.profileName);
                        textViewAgeValue.setText(findProfile.profileAge);
                        textViewLocationValue.setText(findProfile.profileLocation);
                        textViewTennisLevelValue.setText(findProfile.profileTennisLevel);
                        textViewChessLevelValue.setText(findProfile.profileChessLevel);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onClick(View view) {
        Intent intent = getIntent();
        String profileEmailAddress = intent.getStringExtra("Username");

        if (view == SendChat) {
            Intent intentChat = new Intent(this,ChatActivity.class);
            this.startActivity(intentChat);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainMenuInflater = getMenuInflater();
        mainMenuInflater.inflate(R.menu.mainmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.homeMenu){
            Intent intentHome = new Intent(this, HomepageActivity.class);
            this.startActivity(intentHome);
        } else if(item.getItemId() == R.id.myPotentialMatchesMenu){
            Intent intentMyPotentialMatches = new Intent(this, MyPotentialMatchesActivity.class);
            this.startActivity(intentMyPotentialMatches);
        } else if(item.getItemId() == R.id.myMatchesMenu){
            Intent intentMyMatches = new Intent(this, MyMatchesActivity.class);
            this.startActivity(intentMyMatches);
        } else if (item.getItemId() == R.id.chatMenu){
            Intent intentChat = new Intent(this, ChatActivity.class);
            this.startActivity(intentChat);
        } else if (item.getItemId() == R.id.profileMenu){
            Intent intentProfile = new Intent(this, ProfileActivity.class);
            this.startActivity(intentProfile);
        } else if (item.getItemId() == R.id.logoutMenu){
            Intent intentLogout = new Intent(this, MainActivity.class);
            this.startActivity(intentLogout);
        }

        return super.onOptionsItemSelected(item);
    }
}
