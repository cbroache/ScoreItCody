package com.example.codybroache.scoreit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cody Broache on 11/15/2015.
 */
public class ScorecardAdapter extends BaseAdapter {

    private final List<Scorecard> scorecards = new ArrayList<Scorecard>();
    private final Context mContext;
    private LayoutInflater mInflater;

    public ScorecardAdapter(Context context) {
        this.mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return scorecards.size();
    }

    public void clear(){
        scorecards.clear();
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {

        return scorecards.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    public void add(Scorecard scorecard) {
        scorecards.add(scorecard);
        notifyDataSetChanged();
    }

    public boolean exists(Scorecard scorecard){
        for(Scorecard curr : scorecards){
            if(curr.getHomeTeam().equals(scorecard.getHomeTeam())
                    && curr.getAwayTeam().equals(scorecard.getAwayTeam())
                    && curr.getDate().equals(scorecard.getDate())){
                return true;
            }
        }
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Scorecard scorecard = (Scorecard) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.scorecard_item, parent, false);
        }
        // Lookup view for data population
        TextView game = (TextView) convertView.findViewById(R.id.game);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        // Populate the data into the template view using the data object
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(convertView.getContext());
        String dateString = dateFormat.format(scorecard.getDate()).toString();
        game.setText(scorecard.getHomeTeam() + " vs " + scorecard.getAwayTeam());
        date.setText(dateString);


        // Return the completed view to render on screen
        return convertView;
    }
}
