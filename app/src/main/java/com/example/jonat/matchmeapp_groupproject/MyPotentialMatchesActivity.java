package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MyPotentialMatchesActivity extends Activity implements View.OnClickListener {

    private EditText AppTitle;
    private TextView PageTitle, FilterPrompt;
    private Spinner Filter;

    private ListView MyPotentialMatches;

    private int[] ProfilePictures = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    private String[] Names = {"Joe","Meghan","Aaron","Kate"};
    private String[] Availabilities = {"9:00-10:00 AM", "10:00-11:00 AM", "2:00-3:00 PM", "6:00-7:00 PM"};
    private String[] Locations = {"0.7 Miles Away", "0.1 Miles Away", "2.4 Miles Away", "1.5 Miles Away"};
    private String[] SkillLevels = {"Intermediate", "Intermediate", "Beginner", "Advanced"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_potential_matches);

        AppTitle = (EditText) findViewById(R.id.editTextAppTitle);
        PageTitle = (TextView) findViewById(R.id.textViewPageTitle);
        FilterPrompt = (TextView) findViewById(R.id.textViewFilterPrompt);
        Filter = (Spinner) findViewById(R.id.spinnerFilter);
        MyPotentialMatches = (ListView) findViewById(R.id.listViewMyPotentialMatches);

        CustomAdapter customAdapter = new CustomAdapter();
        MyPotentialMatches.setAdapter(customAdapter);
        
        MyPotentialMatches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Names.length;
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

            view = getLayoutInflater().inflate(R.layout.potentialmatcheslayout, null);

            ImageView ProfilePicture = view.findViewById(R.id.imageViewProfilePicture);
            TextView Name = view.findViewById(R.id.textViewName);
            TextView Availability = view.findViewById(R.id.textViewAvailability);
            TextView Location = view.findViewById(R.id.textViewLocation);
            TextView SkillLevel = view.findViewById(R.id.textViewSkillLevel);
            Button Invite = view.findViewById(R.id.buttonInvite);
            Button ViewProfile = view.findViewById(R.id.buttonViewProfile);

            ProfilePicture.setImageResource(ProfilePictures[i]);
            Name.setText(Names[i]);
            Availability.setText(Availabilities[i]);
            Location.setText(Locations[i]);
            SkillLevel.setText(SkillLevels[i]);

            return view;
        }
    }

    @Override
    public void onClick(View view) {

    }

}