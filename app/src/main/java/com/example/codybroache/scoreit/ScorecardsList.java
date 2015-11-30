package com.example.codybroache.scoreit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecards_list);

        prefs = getSharedPreferences("scoreit_user_scorecards", MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.scorecard_list);
        adapter = new ScorecardAdapter(getApplicationContext());
        ArrayList<Scorecard> scorecardSet = new ArrayList<Scorecard>();
        listView.setAdapter(adapter);
        Gson gson = new Gson();
       /* for(int i =0; i <10; i++){
            Scorecard scorecard = new Scorecard("Giants" + i, "Dodgers" + i, new Date(500000 + 1000 * i));
            scorecardSet.add(scorecard);
        }

        String json = gson.toJson(scorecardSet);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("admin", json);
        editor.commit();*/

        String json = prefs.getString("admin", "");
        Type type = new TypeToken<ArrayList<Scorecard>>(){}.getType();
        scorecardSet = gson.fromJson(json, type);
        for (Scorecard card : scorecardSet){
            adapter.add(card);
        }

        bChooseTeams = (ImageButton) findViewById(R.id.addBtn);
        bChooseTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ChooseTeams.class);
                startActivity(i);
            }
        });
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
