package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MyMatchesActivity extends Activity implements View.OnClickListener{

    private EditText etTitle, etMyatches;
    private ListView lvCurrentMatches;

    private String[] NAMES  = {"Mike","Neil","Jyoty","Jonathan","Arjun"};
    private String[] GAMES = {"Tennis","Chess"};
    private String[] DATEANDTIME = {"Monday, 9-11am","Friday 6-8pm","Saturday 1-3pm"};
    private String[] LOCATION = {"Burns Park Tennis Courts","Union Chess Hall"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        lvCurrentMatches = findViewById(R.id.lvCurrentMatches);
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


            return null;
        }
    }

    @Override
    public void onClick(View view) {

    }
}
