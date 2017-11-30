package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MyMatchesActivity extends Activity implements View.OnClickListener{

    private EditText etTitle, etMyatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_matches);
    }

    @Override
    public void onClick(View view) {

    }
}
