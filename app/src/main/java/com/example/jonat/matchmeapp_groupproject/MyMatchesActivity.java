package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyMatchesActivity extends Activity implements View.OnClickListener{

    private TextView tvTitle, tvPendingMatchesReceived, tvPendingMatchesSent, tvConfirmedMatches;
    private ListView lvPendingMatchesReceived, lvPendingMatchesSent, lvConfirmedMatches;
    private FirebaseAuth mAuth;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList<InviteClass> inviteListReceived = new ArrayList<>();
    ArrayList<InviteClass> inviteListSent = new ArrayList<>();

    private int[] ProfilePictures = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    public double[] latLong = new double[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        tvTitle = findViewById(R.id.tvTitle);
        tvPendingMatchesReceived = findViewById(R.id.tvPendingMatchesReceived);
        tvPendingMatchesSent = findViewById(R.id.tvPendingMatchesReceived);
        tvConfirmedMatches= findViewById(R.id.tvConfirmedMatches);

        lvPendingMatchesReceived = findViewById(R.id.lvPendingMatchesReceived);
        lvPendingMatchesSent = findViewById(R.id.lvPendingMatchesSent);
        lvConfirmedMatches = findViewById(R.id.lvConfirmedMatches);

        mAuth = FirebaseAuth.getInstance();

        final DatabaseReference inviteRef = db.getReference("Invites");

        inviteRef.orderByChild("inviteReceiver").equalTo(mAuth.getCurrentUser().getUid().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot inviteReceived : dataSnapshot.getChildren()){
                    InviteClass inviteClass = inviteReceived.getValue(InviteClass.class);
                    inviteListReceived.add(inviteClass);
                }
                CustomAdapter1 customAdapter1 = new CustomAdapter1(inviteListReceived);
                lvPendingMatchesReceived.setAdapter(customAdapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        inviteRef.orderByChild("inviteSender").equalTo(mAuth.getCurrentUser().getUid().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot inviteSent : dataSnapshot.getChildren()){
                    InviteClass inviteClass = inviteSent.getValue(InviteClass.class);
                    inviteListSent.add(inviteClass);
                }
                CustomAdapter2 customAdapter2 = new CustomAdapter2(inviteListSent);
                lvPendingMatchesSent.setAdapter(customAdapter2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        CustomAdapter3 customAdapter3 = new CustomAdapter3();
//        lvConfirmedMatches.setAdapter(customAdapter3);

        lvPendingMatchesReceived.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lvPendingMatchesSent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    class CustomAdapter1 extends BaseAdapter {

        private ArrayList<InviteClass> inviteListReceived;

        public CustomAdapter1(ArrayList<InviteClass> inviteListReceived){
            this.inviteListReceived = inviteListReceived;
        }

        @Override
        public int getCount() {
            return inviteListReceived.size();
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
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.pendingmatcheslayout,null);

            Location locationA = new Location("pointA");
            locationA.setLatitude(inviteListReceived.get(position).inviteReceiverLatitude);
            locationA.setLongitude(inviteListReceived.get(position).inviteReceiverLongitude);
            Location locationB = new Location("pointB");
            locationB.setLatitude(inviteListReceived.get(position).inviteSenderLatitude);
            locationB.setLongitude(inviteListReceived.get(position).inviteSenderLongitude);

            float distance = locationA.distanceTo(locationB) / 5280;

            ImageView ivPicture = view.findViewById(R.id.ivPicture);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvGame = view.findViewById(R.id.tvGame);
            TextView tvDayTime = view.findViewById(R.id.tvDayTime);
            TextView tvLocation = view.findViewById(R.id.tvLocation);

            ivPicture.setImageResource(ProfilePictures[position]);
            tvName.setText(inviteListReceived.get(position).inviteSenderName);
            tvGame.setText(inviteListReceived.get(position).inviteActivity);
            tvDayTime.setText(inviteListReceived.get(position).inviteSlot);
            tvLocation.setText(distance + " Miles Away");

            return view;
        }
    }

    class CustomAdapter2 extends BaseAdapter {

        private ArrayList<InviteClass> inviteListSent;

        public CustomAdapter2(ArrayList<InviteClass> inviteListSent){
            this.inviteListSent = inviteListSent;
        }

        @Override
        public int getCount() { return inviteListSent.size(); }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.pendingmatcheslayout2,null);

            Location locationA = new Location("pointA");
            locationA.setLatitude(inviteListSent.get(position).inviteReceiverLatitude);
            locationA.setLongitude(inviteListSent.get(position).inviteReceiverLongitude);
            Location locationB = new Location("pointB");
            locationB.setLatitude(inviteListSent.get(position).inviteSenderLatitude);
            locationB.setLongitude(inviteListSent.get(position).inviteSenderLongitude);

            float distance = locationA.distanceTo(locationB) / 5280;

            ImageView ivPicture = view.findViewById(R.id.ivPicture);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvGame = view.findViewById(R.id.tvGame);
            TextView tvDayTime = view.findViewById(R.id.tvDayTime);
            TextView tvLocation = view.findViewById(R.id.tvLocation);

            ivPicture.setImageResource(ProfilePictures[position]);
            tvName.setText(inviteListSent.get(position).inviteReceiverName);
            tvGame.setText(inviteListSent.get(position).inviteActivity);
            tvDayTime.setText(inviteListSent.get(position).inviteSlot);
            tvLocation.setText(distance + " Miles Away");
            return view;
        }
    }

//    class CustomAdapter3 extends BaseAdapter {
//        @Override
//        public int getCount() {
//            return NAMES.length;
//        }
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            view = getLayoutInflater().inflate(R.layout.currentmatcheslayout,null);
//            TextView tvName = view.findViewById(R.id.tvName);
//            TextView tvGame = view.findViewById(R.id.tvGame);
//            TextView tvDayTime = view.findViewById(R.id.tvDayTime);
//            TextView tvLocation = view.findViewById(R.id.tvLocation);
//            ImageView ivPicture = view.findViewById(R.id.ivPicture);
//
//            tvName.setText(NAMES[i]);
//            tvGame.setText(GAMES[i]);
//            tvDayTime.setText(DAYTIME[i]);
//            tvLocation.setText(LOCATION[i]);
//            ivPicture.setImageResource(IMAGES[i]);
//
//            return view;
//        }
//    }

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