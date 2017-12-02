package com.example.jonat.matchmeapp_groupproject;

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

    private EditText etTitle, etMyatches;
    private ListView lvCurrentMatches, lvPastMatches;

    private String[] NAMES  = {"Mike","Neil"};
    private String[] GAMES = {"Tennis","Chess"};
    private String[] DAYTIME = {"Friday 6:00-8:00 PM","Saturday 1:00-3:00 PM"};
    private String[] LOCATION = {"Burns Park Tennis Courts","Union Chess Hall"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        lvCurrentMatches = findViewById(R.id.lvCurrentMatches);
        lvPastMatches = findViewById(R.id.lvPastMatches);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        lvCurrentMatches.setAdapter(customAdaptor);
        lvPastMatches.setAdapter(customAdaptor);
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

            view = getLayoutInflater().inflate(R.layout.currentmatcheslayout, null);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvGame = view.findViewById(R.id.tvGame);
            TextView tvDayTime = view.findViewById(R.id.tvDayTime);
            TextView tvLocation = view.findViewById(R.id.tvLocation);

            tvName.setText(NAMES[i]);
            tvGame.setText(GAMES[i]);
            tvDayTime.setText(DAYTIME[i]);
            tvLocation.setText(LOCATION[i]);

            return null;
        }
    }

    @Override
    public void onClick(View view) {

    }
}
