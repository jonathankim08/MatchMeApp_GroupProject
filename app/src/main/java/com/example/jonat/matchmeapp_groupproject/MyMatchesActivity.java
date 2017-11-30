package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MyMatchesActivity extends Activity implements View.OnClickListener{

    private EditText etTitle, etMyatches;

    String[] mobileArray = {"User 1","User 2","User 3","Uber 4", "User 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_my_matches, mobileArray);

        ListView listView = (ListView) findViewById(R.id.user_list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}
