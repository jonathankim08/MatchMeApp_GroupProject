package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyPotentialMatchesActivity extends Activity implements View.OnClickListener {

    private TextView AppTitle, PageTitle, FilterPrompt;
    private Spinner Filter;
    private ListView MyPotentialMatches;
    private FirebaseAuth mAuth;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList<MatchPoolClass> matchPoolList = new ArrayList<>();

    private int[] ProfilePictures = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    public double[] latLong = new double[2];
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_potential_matches);

        Intent intent = getIntent();
        final String activity = intent.getStringExtra("Activity");
        final String day = intent.getStringExtra("Day");
        final String month = intent.getStringExtra("Month");
        final String slot = intent.getStringExtra("Slot");
        final String profileEmailAddress = intent.getStringExtra("Username");

        AppTitle = (TextView) findViewById(R.id.textViewAppTitle);
        PageTitle = (TextView) findViewById(R.id.textViewPageTitle);
        FilterPrompt = (TextView) findViewById(R.id.textViewFilterPrompt);
        Filter = (Spinner) findViewById(R.id.spinnerFilter);
        MyPotentialMatches = (ListView) findViewById(R.id.listViewMyPotentialMatches);

        mAuth = FirebaseAuth.getInstance();

        final DatabaseReference matchPoolRef = db.getReference("MatchPool");
        final DatabaseReference profileRef = db.getReference("Profiles");

        profileRef.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profileRef.child(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ProfileClass findProfile = new ProfileClass();
                        findProfile = dataSnapshot.getValue(ProfileClass.class);
                        latLong[0] = findProfile.profileLatitude;
                        latLong[1] = findProfile.profileLongitude;
                        username = findProfile.profileName;
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

        matchPoolRef.orderByChild("matchString").equalTo(activity + day + month + slot).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot match : dataSnapshot.getChildren()){
                   final String userId = match.child("matchPoolUserId").getValue().toString();

                    //skip the records for current user
                    if (!userId.equals(mAuth.getCurrentUser().getUid()) ){
                        MatchPoolClass matchPoolClass = match.getValue(MatchPoolClass.class);
                        matchPoolList.add(matchPoolClass);
                    }
                }
                CustomAdapter customAdapter = new CustomAdapter(matchPoolList);
                MyPotentialMatches.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    class CustomAdapter extends BaseAdapter {

        private ArrayList<MatchPoolClass> matchPoolList;

        public CustomAdapter(ArrayList<MatchPoolClass> matchPoolList){
            this.matchPoolList = matchPoolList;
        }

        @Override
        public int getCount() { return matchPoolList.size(); }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.potentialmatcheslayout, null);

            ImageView ProfilePicture = view.findViewById(R.id.imageViewProfilePicture);
            TextView Name = view.findViewById(R.id.textViewName);
            TextView Availability = view.findViewById(R.id.textViewAvailability);
            TextView Location = view.findViewById(R.id.textViewLocation);
            TextView SkillLevel = view.findViewById(R.id.textViewSkillLevel);

            final DatabaseReference inviteRef = db.getReference("Invites");

            final Button Invite = view.findViewById(R.id.buttonInvite);
            Invite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MyPotentialMatchesActivity.this, "You have invited " + matchPoolList.get(position).matchPoolProfileName + "!", Toast.LENGTH_SHORT).show();
                    InviteClass inviteClass = new InviteClass(mAuth.getCurrentUser().getUid(), matchPoolList.get(position).matchPoolUserId, username, matchPoolList.get(position).matchPoolProfileName, matchPoolList.get(position).matchPoolActivity, matchPoolList.get(position).matchPoolSlot, matchPoolList.get(position).matchPoolDay, matchPoolList.get(position).matchPoolMonth, "Open", latLong[0], latLong[1], matchPoolList.get(position).matchPoolProfileLatitude, matchPoolList.get(position).matchPoolProfileLongitude);
                    inviteRef.push().setValue(inviteClass);
                    Invite.setText("Invited");
                    Invite.setEnabled(false);
                }
            });

            Button ViewProfile = view.findViewById(R.id.buttonViewProfile);

            ViewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            Location locationA = new Location("pointA");
            locationA.setLatitude(latLong[0]);
            locationA.setLongitude(latLong[1]);
            Location locationB = new Location("pointB");
            locationB.setLatitude(matchPoolList.get(position).matchPoolProfileLatitude);
            locationB.setLongitude(matchPoolList.get(position).matchPoolProfileLongitude);

            float distance = locationA.distanceTo(locationB) / 5280;

            ProfilePicture.setImageResource(ProfilePictures[position]);
            Name.setText(matchPoolList.get(position).matchPoolProfileName);
            Availability.setText(matchPoolList.get(position).matchPoolSlot);
            Location.setText(distance + " Miles Away");
            if (matchPoolList.get(position).matchPoolActivity == "Tennis"){
                SkillLevel.setText(matchPoolList.get(position).matchPoolProfileTennisLevel);
            } else {
                SkillLevel.setText(matchPoolList.get(position).matchPoolProfileChessLevel);
            }

            return view;
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainMenuInflater = getMenuInflater();
        mainMenuInflater.inflate(R.menu.mainmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = getIntent();
        String profileEmailAddress = intent.getStringExtra("Username");

        if (item.getItemId() == R.id.homeMenu){
            Intent intentHome = new Intent(this,HomepageActivity.class);
            intentHome.putExtra("Username", profileEmailAddress);
            this.startActivity(intentHome);
        } else if(item.getItemId() == R.id.myPotentialMatchesMenu){
            Intent intentMyPotentialMatches = new Intent(this,MyPotentialMatchesActivity.class);
            intentMyPotentialMatches.putExtra("Username", profileEmailAddress);
            this.startActivity(intentMyPotentialMatches);
        } else if(item.getItemId() == R.id.myMatchesMenu){
            Intent intentMyMatches = new Intent(this,MyMatchesActivity.class);
            intentMyMatches.putExtra("Username", profileEmailAddress);
            this.startActivity(intentMyMatches);
        } else if (item.getItemId() == R.id.chatMenu){
            Intent intentChat = new Intent(this,ChatActivity.class);
            intentChat.putExtra("Username", profileEmailAddress);
            this.startActivity(intentChat);
        } else if (item.getItemId() == R.id.profileMenu){
            Intent intentProfile = new Intent(this,ProfileActivity.class);
            intentProfile.putExtra("Username", profileEmailAddress);
            this.startActivity(intentProfile);
        } else if (item.getItemId() == R.id.logoutMenu){
            Intent intentLogout = new Intent(this,MainActivity.class);
            intentLogout.putExtra("Username", profileEmailAddress);
            this.startActivity(intentLogout);
        }

        return super.onOptionsItemSelected(item);
    }
}