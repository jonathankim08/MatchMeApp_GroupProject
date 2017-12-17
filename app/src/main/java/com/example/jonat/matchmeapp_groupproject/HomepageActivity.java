package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomepageActivity extends Activity {

    private Spinner dateSpinner,monthSpinner;
    private ListView slotListView;
    protected RadioGroup radioGroupactivity;
    protected RadioButton chessRadioButton,tennisRadioButton;
    protected String activity, day, month;
    private String [] slots = {"8:00-9:00 AM", "9:00-10:00 AM", "10:00-11:00 AM", "11:00 AM-12:00 PM", "12:00-1:00 PM",
            "1:00-2:00 PM", "2:00-3:00 PM", "3:00-4:00 PM", "4:00-5:00 PM", "5:00-6:00 PM", "6:00-7:00 PM", "7:00-8:00 PM"};
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        radioGroupactivity = (RadioGroup) findViewById(R.id.radioGroup2);
        chessRadioButton = (RadioButton) findViewById(R.id.radioButtonChess);
        tennisRadioButton = (RadioButton) findViewById(R.id.radioButtonTennis);

        slotListView = (ListView)  findViewById(R.id.ListViewcalenderslots);
        dateSpinner =  (Spinner) findViewById(R.id.spinnerDate);
        monthSpinner = (Spinner) findViewById(R.id.spinnerMonth);

        mAuth = FirebaseAuth.getInstance();

        CustomAdapter customadapter = new CustomAdapter();
        slotListView.setAdapter(customadapter);

        slotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                day = dateSpinner.getSelectedItem().toString();
                month = monthSpinner.getSelectedItem().toString();

                if (chessRadioButton.isChecked()) {
                    activity = "chess";
                    addToDatabase(day, month, i);
                    sendToPotential(activity, day, month, i);
                } else if (tennisRadioButton.isChecked()) {
                    activity = "tennis";
                    addToDatabase(day, month, i);
                    sendToPotential(activity, day, month, i);
                } else if (!(tennisRadioButton.isChecked() && chessRadioButton.isChecked())) {
                    Toast.makeText(HomepageActivity.this, "Please select an activity!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }

        private void addToDatabase(String day, String month, int i) {
            final String tempDay = day;
            final String tempMonth = month;
            final int tempI = i;

            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference matchPoolRef = db.getReference("MatchPool");
            final DatabaseReference profileRef = db.getReference("Profiles");

            profileRef.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    profileRef.child(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            final String tempProfileName, tempProfileChessLevel, tempProfileTennisLevel;
                            final double tempProfileLatitude, tempProfileLongitude;

                            ProfileClass profileClass = new ProfileClass();
                            profileClass = dataSnapshot.getValue(ProfileClass.class);

                            tempProfileName = profileClass.profileName;
                            tempProfileChessLevel = profileClass.profileChessLevel;
                            tempProfileTennisLevel = profileClass.profileTennisLevel;
                            tempProfileLatitude = profileClass.profileLatitude;
                            tempProfileLongitude = profileClass.profileLongitude;

                            final MatchPoolClass myMatchPool = new MatchPoolClass(mAuth.getCurrentUser().getUid(), activity, tempDay, tempMonth, slots[tempI],"Open", tempProfileName, tempProfileChessLevel, tempProfileTennisLevel, tempProfileLatitude, tempProfileLongitude);

                            matchPoolRef.orderByChild("matchDuplicateSlot").equalTo(mAuth.getCurrentUser().getUid()+ activity + tempDay + tempMonth + slots[tempI]).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() == null){
                                        matchPoolRef.push().setValue(myMatchPool);
                                    } else {
                                        Toast.makeText(HomepageActivity.this, "The chosen slot already exists!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

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

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return slots.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.calenderslots, null);
            TextView textViewSlot = view.findViewById(R.id.textViewslot) ;

            textViewSlot.setText(slots[i]);

            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainMenuInflater = getMenuInflater();
        mainMenuInflater.inflate(R.menu.mainmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void sendToPotential(String activity, String day, String month, int i) {
        Intent intent = getIntent();
        String profileEmailAddress = intent.getStringExtra("Username");

        Intent intentMyPotentialMatches = new Intent(this, MyPotentialMatchesActivity.class);
        intentMyPotentialMatches.putExtra("Activity", activity);
        intentMyPotentialMatches.putExtra("Day", day);
        intentMyPotentialMatches.putExtra("Month", month);
        intentMyPotentialMatches.putExtra("Slot", slots[i]);
        intentMyPotentialMatches.putExtra("Username", profileEmailAddress);

        this.startActivity(intentMyPotentialMatches);
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