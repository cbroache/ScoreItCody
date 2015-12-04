package com.example.codybroache.scoreit;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Brandon on 11/4/2015.
 */
public class ChooseTeams extends Activity {
    private AutoCompleteTextView home;
    private AutoCompleteTextView away;
    private Button bSubmit;
    String username;
    static private final String TAG_USER = "scoreit_username";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.choose_teams);

        Intent intent = getIntent();
        username = intent.getExtras().getString(TAG_USER);

        away = (AutoCompleteTextView) findViewById(R.id.auto_away);
        away.setThreshold(1);
        home = (AutoCompleteTextView) findViewById(R.id.auto_home);
        home.setThreshold(1);

        ArrayList<String> arr = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        Collections.addAll(arr, getResources().getStringArray(R.array.teams));
        Collections.addAll(arr2, getResources().getStringArray(R.array.teams));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, arr);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.dropdown_item, arr2);

        away.setAdapter(adapter);
        home.setAdapter(adapter2);

        away.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    // on focus off
                    String str = away.getText().toString();
                    if (str.equals("")) {
                        return;
                    }
                    ListAdapter listAdapter = away.getAdapter();
                    int count = listAdapter.getCount();
                    for (int i = 0; i < count; i++) {
                        String temp = listAdapter.getItem(i).toString();
                        if (str.compareTo(temp) == 0) {
                            return;
                        }
                    }
                    away.setText("");
                    Toast.makeText(ChooseTeams.this, "That was not a valid team. Choose from the " +
                            "autocomplete suggestions.", Toast.LENGTH_LONG).show();
                }
            }
        });

        away.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (away.getText().toString().compareTo(home.getText().toString()) == 0) {
                    away.setText("");
                    Toast.makeText(ChooseTeams.this, "The teams cannot be the same; enter " +
                            "another away team.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        home.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    // on focus off
                    String str = home.getText().toString();
                    if (str.equals("")) {
                        return;
                    }
                    ListAdapter listAdapter = home.getAdapter();
                    int count = listAdapter.getCount();
                    for (int i = 0; i < count; i++) {
                        String temp = listAdapter.getItem(i).toString();
                        if (str.compareTo(temp) == 0) {
                            return;
                        }
                    }
                    home.setText("");
                    Toast.makeText(ChooseTeams.this, "That was not a valid team. Choose from the " +
                            "autocomplete suggestions.", Toast.LENGTH_LONG).show();
                }
            }
        });

        home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (home.getText().toString().compareTo(away.getText().toString()) == 0) {
                    home.setText("");
                    Toast.makeText(ChooseTeams.this, "The teams cannot be the same; enter " +
                            "another home team.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        bSubmit = (Button) findViewById(R.id.submit_teams);
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean awayOk = false;
                boolean homeOk = false;

                String str = home.getText().toString();
                if (str.equals("")) {
                    return;
                }
                ListAdapter listAdapter = home.getAdapter();
                int count = listAdapter.getCount();
                for (int i = 0; i < count; i++) {
                    String temp = listAdapter.getItem(i).toString();
                    if (str.compareTo(temp) == 0) {
                        homeOk = true;
                    }
                }
                if (!homeOk) {
                    home.setText("");
                }

                String str2 = away.getText().toString();
                if (str2.equals("")) {
                    return;
                }
                ListAdapter listAdapter2 = away.getAdapter();
                int count2 = listAdapter2.getCount();
                for (int i = 0; i < count2; i++) {
                    String temp = listAdapter2.getItem(i).toString();
                    if (str2.compareTo(temp) == 0) {
                        awayOk = true;
                    }
                }
                if (!awayOk) {
                    away.setText("");
                }

                if (!home.getText().toString().equals("") && !away.getText().toString().equals("")) {
                    Intent intent = new Intent(ChooseTeams.this, SetLineup1.class);
                    intent.putExtra("Home Team", home.getText().toString());
                    intent.putExtra("Away Team", away.getText().toString());
                    intent.putExtra(TAG_USER, username);
                    startActivity(intent);
                } else {
                    Toast.makeText(ChooseTeams.this, "Please enter two valid teams before advancing.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}