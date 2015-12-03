package com.example.codybroache.scoreit;

import android.content.Context;
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
    SharedPreferences prefs;
    static private final String TAG_US = "scoreit_user_scorecards";

    public ScorecardAdapter(Context context, ArrayList<Scorecard> scorecards) {
        super(context, 0, scorecards);
        this.scorecards = scorecards;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        game.setText(scorecard.getHomeTeam() + " vs " + scorecard.getAwayTeam());
        date.setText(dateString);

        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
        deleteButton.setTag(position);

        deleteButton.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer index = (Integer) v.getTag();
                        Scorecard toRemove = scorecards.get(index.intValue());
                        scorecards.remove(toRemove);
                        notifyDataSetChanged();

                        //delete from shared preferances
                        Gson gson = new Gson();
                        prefs = v.getContext().getSharedPreferences(TAG_US, v.getContext().MODE_PRIVATE);
                        String json = prefs.getString("admin", "");
                        Type type = new TypeToken<ArrayList<Scorecard>>(){}.getType();
                        ArrayList<Scorecard> scorecardSet = new ArrayList<Scorecard>();
                        scorecardSet = gson.fromJson(json, type);
                        scorecardSet.remove(toRemove);
                        json = gson.toJson(scorecardSet);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("admin", json);
                        editor.commit();
                    }
                }
        );

        // Return the completed view to render on screen
        return convertView;
    }
}
