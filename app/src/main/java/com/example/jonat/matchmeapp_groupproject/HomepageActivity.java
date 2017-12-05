package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

public class HomepageActivity extends Activity {

    public HomepageActivity() {
    }

    private Button buttonSlot1, buttonSlot2, buttonSlot3, buttonSlot4, buttonSlot5,buttonSlot6;
    private RadioButton radiobuttonchess, radiobuttontennis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonSlot1 = findViewById(R.id.slot1);
        buttonSlot2 = findViewById(R.id.slot2);
        buttonSlot3 = findViewById(R.id.slot3);
        buttonSlot4 = findViewById(R.id.slot4);
        buttonSlot5 = findViewById(R.id.slot5);
        buttonSlot6 = findViewById(R.id.slot6);
        radiobuttonchess = findViewById(R.id.chessRadButton);
        radiobuttontennis = findViewById(R.id.tennisRadButton);




    }

}
