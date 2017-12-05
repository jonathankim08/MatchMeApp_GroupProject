package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HomepageActivity extends Activity {

    //get email address
    Intent intent = getIntent();
    String profileEmailAddress = intent.getStringExtra("Username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
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
            Intent intentHome = new Intent(this,HomepageActivity.class);
            intentHome.putExtra("Username", profileEmailAddress);
            this.startActivity(intentHome);
        }else if(item.getItemId() == R.id.myPotentialMatchesMenu){
            Intent intentMyPotentialMatches = new Intent(this,MyPotentialMatchesActivity.class);
            intentMyPotentialMatches.putExtra("Username", profileEmailAddress);
            this.startActivity(intentMyPotentialMatches);
        }else if(item.getItemId() == R.id.myMatchesMenu){
            Intent intentMyMatches = new Intent(this,MyMatchesActivity.class);
            intentMyMatches.putExtra("Username", profileEmailAddress);
            this.startActivity(intentMyMatches);
        }else if (item.getItemId() == R.id.chatMenu){
            Intent intentChat = new Intent(this,ChatActivity.class);
            intentChat.putExtra("Username", profileEmailAddress);
            this.startActivity(intentChat);
        }else if (item.getItemId() == R.id.updateProfileMenu){
            Intent intentUpdateProfile = new Intent(this,RegistrationActivity.class);
            intentUpdateProfile.putExtra("Username", profileEmailAddress);
            this.startActivity(intentUpdateProfile);
        }else if (item.getItemId() == R.id.logoutMenu){
            Intent intentLogout = new Intent(this,MainActivity.class);
            intentLogout.putExtra("Username", profileEmailAddress);
            this.startActivity(intentLogout);
        }

        return super.onOptionsItemSelected(item);
    }
}
