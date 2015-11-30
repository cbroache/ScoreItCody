package com.example.codybroache.scoreit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Brandon on 11/17/2015.
 */
public class PlayerListAdapter extends BaseAdapter {

    private final List<Player> mItems = new ArrayList<Player>();
    private final Context mContext;

    public PlayerListAdapter(Context context) {
        mContext = context;
    }

    public void add(Player player) {
        for (Player p : mItems){
            if (p.getLastName().equals(player.getLastName()) && p.getFirstName().equals(player.getFirstName())) {
                return;
            }
        }
        mItems.add(player);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return mItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Player player = (Player) getItem(position);

        View itemLayout = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemLayout = inflater.inflate(R.layout.player, parent, false);
        } else {
            itemLayout = convertView;
        }

        final TextView playerName = (TextView) itemLayout.findViewById(R.id.player_name);
        playerName.setText(player.getLastName());

        final TextView number = (TextView) itemLayout.findViewById(R.id.player_number);
        number.setText(String.valueOf(player.getNumber()));



        return itemLayout;
    }

    public List<Player> getItems() {
        return this.mItems;
    }

    @Override
    public void notifyDataSetChanged() {
        Collections.sort(mItems, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                return lhs.getLastName().toLowerCase().compareTo(rhs.getLastName().toLowerCase());
            }
        });
        super.notifyDataSetChanged();
    }
}
