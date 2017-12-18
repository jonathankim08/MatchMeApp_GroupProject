package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends Activity implements View.OnClickListener {

    private EditText editTextChat;
    private Button buttonSubmit;
    private ListView listViewMessages;
    private Spinner spinnerProfiles;

    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> profileList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> profileListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editTextChat = findViewById(R.id.editTextChat);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        listViewMessages = findViewById(R.id.listViewMessages);
        spinnerProfiles = findViewById(R.id.spinnerProfiles);

        profileListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, profileList);
        profileListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfiles.setAdapter(profileListAdapter);
        spinnerProfiles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        buttonSubmit.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(this, R.layout.chatlayout, R.id.textViewChat, list);
        listViewMessages.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Chat");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String chat;
                chat = dataSnapshot.getValue().toString();
                list.add(chat);
                adapter.notifyDataSetChanged();
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

        DatabaseReference profileRef = database.getReference("Profiles");
        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot profile : dataSnapshot.getChildren()) {
                    for (DataSnapshot userObj : profile.getChildren()){
                        profileList.add(userObj.child("profileName").getValue().toString());
                    }

                }

                profileListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String messagesSender = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference chatRef = database.getReference("Chat");

        //String messagesReceiver =
        chatRef.child(messagesSender).push().setValue(editTextChat.getText().toString());
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