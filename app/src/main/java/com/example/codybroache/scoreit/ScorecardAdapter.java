package com.example.codybroache.scoreit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Cody Broache on 11/15/2015.
 */
public class ScorecardAdapter extends ArrayAdapter {

    private ArrayList<Scorecard> scorecards;
    private Activity parentActivity;
    SharedPreferences prefs;
    String username;
    static private final String TAG_US = "scoreit_user_scorecards";

    public ScorecardAdapter(Context context, ArrayList<Scorecard> scorecards, Activity parentActivity, String username) {
        super(context, 0, scorecards);
        this.scorecards = scorecards;
        this.parentActivity = parentActivity;
        this.username = username;
    }


    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final Scorecard scorecard = (Scorecard) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.scorecard_item, parent, false);
        }
        // Lookup view for data population
        TextView game = (TextView) convertView.findViewById(R.id.game);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        // Populate the data into the template view using the data object
        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        String dateString = format.format(scorecard.getDate());
        Random i = new Random();
        final String gameText = scorecard.getHomeTeam() + " vs. " + scorecard.getAwayTeam();
        game.setText(gameText);
        date.setText(dateString);

        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
        deleteButton.setTag(position);

        deleteButton.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(parentActivity);
                        alert.setTitle("Delete");
                        alert.setMessage("Are you sure you want to delete " + gameText + "?");
                        alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                        alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            int index = (Integer) v.getTag();
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do your work here
                                scorecards.remove(index);
                                notifyDataSetChanged();

                                //delete from shared preferances
                                Gson gson = new Gson();
                                prefs = parentActivity.getSharedPreferences(TAG_US, parentActivity.MODE_PRIVATE);
                                String json = prefs.getString(username, "");
                                Type type = new TypeToken<ArrayList<Scorecard>>(){}.getType();
                                ArrayList<Scorecard> scorecardSet = gson.fromJson(json, type);
                                scorecardSet.remove(index);
                                json = gson.toJson(scorecardSet);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString(username, json);
                                editor.commit();
                                Toast.makeText(parentActivity, "Scorecard Deleted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        alert.show();
                    }
                }
        );

        // Return the completed view to render on screen
        return convertView;
    }
}
