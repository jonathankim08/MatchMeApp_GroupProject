package com.example.jonat.matchmeapp_groupproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class HomepageActivity extends Activity {

    private Spinner dateSpinner,monthSpinner;
    private ListView slotListView;
    protected RadioGroup radioGroupactivity;
    protected RadioButton chessRadioButton,tennisRadioButton;
    protected String activity, day, month;
    private String [] slots = {"8am to 9am", "9am to 10am", "10am to 11am", "11am to 12pm", "12pm to 1pm",
            "1pm to 2pm", "2pm to 3pm", "3pm to 4pm",
            "4pm to 5pm", "5pm to 6pm", "6pm to 7pm", "7pm to 8pm"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        radioGroupactivity = (RadioGroup) findViewById(R.id.radioGroup2);
        chessRadioButton = (RadioButton) findViewById(R.id.radioButtonChess);
        tennisRadioButton = (RadioButton) findViewById(R.id.radioButtonTennis);

        slotListView = (ListView)  findViewById(R.id.ListViewcalenderslots);
        dateSpinner =  (Spinner) findViewById(R.id.spinnerDate);
        //dateSpinner.setOnItemSelectedListener(this);

        monthSpinner = (Spinner) findViewById(R.id.spinnerMonth);
        //monthSpinner.setOnItemSelectedListener(this);

        //List view code follows:
        CustomAdapter customadapter = new CustomAdapter();
        slotListView.setAdapter(customadapter);

        slotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (chessRadioButton.isChecked())
                    activity = "chess";
                if (tennisRadioButton.isChecked())
                    activity = "tennis";

                Toast.makeText(HomepageActivity.this, "You clicked " + activity + " for " + slots[i], Toast.LENGTH_SHORT).show();

                // InviteClass userInvite = new InviteClass(profileEmailAddress, activity, day, month, slots[i],"Open");
                // spinner needs work for the above constructor to work

            }
        });


        }
    /*
    public class SpinnerActivity extends  Activity implements AdapterView.OnItemSelectedListener

    {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            ;

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    */



    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return slots.length;
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

            view = getLayoutInflater().inflate(R.layout.calenderslots, null);
            TextView textViewSlot = view.findViewById(R.id.textViewslot) ;

            textViewSlot.setText(slots[i]);

            return view;

        }
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
            this.startActivity(intentHome);
        } else if(item.getItemId() == R.id.myPotentialMatchesMenu){
            Intent intentMyPotentialMatches = new Intent(this,MyPotentialMatchesActivity.class);
            this.startActivity(intentMyPotentialMatches);
        } else if(item.getItemId() == R.id.myMatchesMenu){
            Intent intentMyMatches = new Intent(this,MyMatchesActivity.class);
            this.startActivity(intentMyMatches);
        } else if (item.getItemId() == R.id.chatMenu){
            Intent intentChat = new Intent(this,ChatActivity.class);
            this.startActivity(intentChat);
        } else if (item.getItemId() == R.id.profileMenu){
            Intent intentProfile = new Intent(this,ProfileActivity.class);
            this.startActivity(intentProfile);
        } else if (item.getItemId() == R.id.logoutMenu){
            Intent intentLogout = new Intent(this,MainActivity.class);
            this.startActivity(intentLogout);
        }

        return super.onOptionsItemSelected(item);
    }
}