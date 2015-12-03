package com.example.codybroache.scoreit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
    static private final String TAG_US = "scoreit_user_scorecards";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecards_list);

        prefs = getSharedPreferences(TAG_US, MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.scorecard_list);
        ArrayList<Scorecard> scorecardSet = new ArrayList<Scorecard>();
        adapter = new ScorecardAdapter(getApplicationContext(), scorecardSet);
        RelativeLayout footer = (RelativeLayout) getLayoutInflater().inflate(R.layout.footer, null);
        listView.addFooterView(footer);
        Button footerButton = (Button) footer.findViewById(R.id.create_scorecard);
        footerButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseTeams.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        Gson gson = new Gson();
        for(int i =0; i <10; i++){
            Scorecard scorecard = new Scorecard();
            scorecardSet.add(scorecard);
        }

        String json = gson.toJson(scorecardSet);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("admin", json);
        editor.commit();
//
//        json = prefs.getString("admin", "");
//        Type type = new TypeToken<ArrayList<Scorecard>>(){}.getType();
//        scorecardSet = gson.fromJson(json, type);

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
