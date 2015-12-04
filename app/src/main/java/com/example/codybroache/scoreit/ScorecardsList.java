package com.example.codybroache.scoreit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class ScorecardsList extends AppCompatActivity {

    ScorecardAdapter adapter;
    ListView listView;
    SharedPreferences prefs;
    private ImageButton bChooseTeams;
    String username;
    ArrayList<Scorecard> scorecardSet = new ArrayList<Scorecard>();
    Gson gson = new Gson();
    String json;
    static private final String TAG_USER = "scoreit_username";
    static private final String TAG_US = "scoreit_user_scorecards";
    static private final String TAG_POS = "scorecard_position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecards_list);

        //get username
        Intent intent = getIntent();
        username = intent.getExtras().getString(TAG_USER);

        prefs = getSharedPreferences(TAG_US, MODE_PRIVATE);

        //set scorecard adapter and footer
        listView = (ListView) findViewById(R.id.scorecard_list);
        adapter = new ScorecardAdapter(getApplicationContext(), scorecardSet, ScorecardsList.this, username);
        RelativeLayout footer = (RelativeLayout) getLayoutInflater().inflate(R.layout.footer, null);
        listView.addFooterView(footer);
        Button footerButton = (Button) footer.findViewById(R.id.create_scorecard);
        footerButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseTeams.class);
                intent.putExtra(TAG_USER, username);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);

        //below code used to generate sample data for debugging purposes

//        for(int i =0; i <10; i++){
//            Scorecard scorecard = new Scorecard();
//            scorecardSet.add(scorecard);
//        }
//
//        String json = gson.toJson(scorecardSet);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(username, json);
//        editor.commit();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ScorecardsList.this, ScorecardOverview.class);
                intent.putExtra(TAG_USER, username);
                intent.putExtra(TAG_POS, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Scorecard> toAdd;
        String json = prefs.getString(username, "");
        Type type = new TypeToken<ArrayList<Scorecard>>(){}.getType();
        toAdd =  gson.fromJson(json, type);
        adapter.clear();
        adapter.notifyDataSetChanged();
        scorecardSet.addAll(toAdd);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scorecards_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
