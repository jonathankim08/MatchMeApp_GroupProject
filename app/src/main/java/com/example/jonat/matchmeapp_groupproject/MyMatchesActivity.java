package com.example.jonat.matchmeapp_groupproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyMatchesActivity extends Activity implements View.OnClickListener{

    private TextView tvTitle, tvPendingMatches, tvConfirmedMatches;
    private ListView lvPendingMatches, lvConfirmedMatches, lvCurrentMatches;
    private Button buttonSendChat;

    private String[] NAMES  = {"Joe","Arjun"};
    private String[] GAMES = {"Tennis","Chess"};
    private String[] DAYTIME = {"Monday 9:00-10:00 AM","Saturday 1:00-3:00 PM"};
    private String[] LOCATION = {"Public Courts","Chess Hall"};
    private int[] IMAGES = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        tvTitle = findViewById(R.id.tvTitle);
        tvPendingMatches = findViewById(R.id.tvPendingMatches);
        tvConfirmedMatches = findViewById(R.id.tvConfirmedMatches);

        lvPendingMatches = findViewById(R.id.lvPendingMatches);
        lvConfirmedMatches = findViewById(R.id.lvConfirmedMatches);
        //lvCurrentMatches = findViewById(R.id.lvConfirmedMatches);

        buttonSendChat = findViewById(R.id.buttonSendChat);
        buttonSendChat.setOnClickListener(this);


        CustomAdaptor customAdaptor = new CustomAdaptor();
        lvPendingMatches.setAdapter(customAdaptor);
        lvConfirmedMatches.setAdapter(customAdaptor);

        lvPendingMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lvConfirmedMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

    }

    class CustomAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return NAMES.length;
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

            view = getLayoutInflater().inflate(R.layout.currentmatcheslayout,null);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvGame = view.findViewById(R.id.tvGame);
            TextView tvDayTime = view.findViewById(R.id.tvDayTime);
            TextView tvLocation = view.findViewById(R.id.tvLocation);
            ImageView ivPicture = view.findViewById(R.id.ivPicture);

            tvName.setText(NAMES[i]);
            tvGame.setText(GAMES[i]);
            tvDayTime.setText(DAYTIME[i]);
            tvLocation.setText(LOCATION[i]);
            ivPicture.setImageResource(IMAGES[i]);

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