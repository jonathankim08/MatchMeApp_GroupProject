package com.example.jonat.matchmeapp_groupproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyMatchesActivity extends Activity implements View.OnClickListener{

    private TextView tvTitle, tvCurrentMatches, tvPastMatches;
    private ListView lvCurrentMatches, lvPastMatches;

    private String[] NAMES  = {"Jyoty","Arjun"};
    private String[] GAMES = {"Tennis","Chess"};
    private String[] DAYTIME = {"Friday 6:00-8:00 PM","Saturday 1:00-3:00 PM"};
    private String[] LOCATION = {"Public Courts","Chess Hall"};
    private int[] IMAGES = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        tvTitle = findViewById(R.id.tvTitle);
        tvCurrentMatches = findViewById(R.id.tvCurrentMatches);
        tvPastMatches = findViewById(R.id.tvPastMatches);
        lvCurrentMatches = findViewById(R.id.lvCurrentMatches);
        lvPastMatches = findViewById(R.id.lvPastMatches);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        lvCurrentMatches.setAdapter(customAdaptor);
        lvPastMatches.setAdapter(customAdaptor);


        lvCurrentMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyMatchesActivity.this, "You Clicked: " + NAMES[i], Toast.LENGTH_SHORT).show();
            }
        });

        lvPastMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyMatchesActivity.this, "You Clicked: " + NAMES[i], Toast.LENGTH_SHORT).show();
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
}