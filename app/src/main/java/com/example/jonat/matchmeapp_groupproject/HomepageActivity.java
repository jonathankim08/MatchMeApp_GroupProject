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
    private String [] slots = {"8:00 am to 9:00 am", "9:00 am to 10:00 am", "10:00 am to 11:00 am", "11:00 am to 12:00 pm", "12:00 pm to 1:00 pm",
            "1:00 pm to 2:00 pm", "2:00 pm to 3:00 pm", "3:00 pm to 4:00 pm", "4:00 pm to 5:00 pm", "5:00 pm to 6:00 pm", "6:00 pm to 7:00 pm", "7:00 pm to 8:00 pm"};
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
        //dateSpinner.setOnItemSelectedListener(this);

        monthSpinner = (Spinner) findViewById(R.id.spinnerMonth);
        //monthSpinner.setOnItemSelectedListener(this);

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
            Intent intent = getIntent();
            final String profileEmailAddress = intent.getStringExtra("Username");

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference matchPoolRef = db.getReference("MatchPool");

            MatchPoolClass myMatchPool = new MatchPoolClass(profileEmailAddress, activity, day, month, slots[i],"Open");
            matchPoolRef.push().setValue(myMatchPool);
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

        Intent intentMyPotentialMatches = new Intent(this,MyPotentialMatchesActivity.class);
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