package com.example.codybroache.scoreit;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class ScorecardOverview extends AppCompatActivity {

    ArrayList<Scorecard> scorecards;
    Scorecard scorecard;
    Team homeTeam;
    Team awayTeam;
    SharedPreferences prefs;
    int totalInnings;
    String username;
    int position;
    Gson gson = new Gson();
    static private final String TAG_USER = "scoreit_username";
    static private final String TAG_US = "scoreit_user_scorecards";
    static private final String TAG_POS = "scorecard_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_scorecard_overview);
        //test view
        setContentView(R.layout.activity_scorecard_overview_test);

        //get username
        Intent intent = getIntent();
        username = intent.getExtras().getString(TAG_USER);
        position = intent.getExtras().getInt(TAG_POS);

        prefs = getSharedPreferences(TAG_US, MODE_PRIVATE);

        String json = prefs.getString(username, "");
        Type type = new TypeToken<ArrayList<Scorecard>>(){}.getType();
        scorecards =  gson.fromJson(json, type);
        scorecard = scorecards.get(position);



        //test scorecard
        scorecard = genFakeScorecard();

        // Lookup view for data population
        TextView game = (TextView) findViewById(R.id.game);
        TextView date = (TextView) findViewById(R.id.date);
        // Populate the data into the template view using the data object
        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        String dateString = format.format(scorecard.getDate());
        final String gameText = scorecard.getHomeTeam().getNickname() + " vs. " + scorecard.getAwayTeam().getNickname();
        game.setText(gameText);
        date.setText(dateString);


        homeTeam = scorecard.getHomeTeam();
        awayTeam = scorecard.getAwayTeam();
        setTotalInnings(homeTeam);
        setTotalInnings(awayTeam);
        genTable(homeTeam);
        genTable(awayTeam);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scorecard_overview, menu);
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

    public void genTable(Team team){
        TableLayout table;
        if(team.getHome()){
            table = (TableLayout)findViewById(R.id.homeTable);
            TextView homeTeamName = (TextView) findViewById(R.id.homeTeamName);
            homeTeamName.setText(team.getNickname());
        }
        else{
            table = (TableLayout)findViewById(R.id.awayTable);
            TextView awayTeamName = (TextView) findViewById(R.id.awayTeamName);
            awayTeamName.setText(team.getNickname());
        }
        table.setStretchAllColumns(true);
        table.bringToFront();


        table.addView(genHeaders());
        TableRow row;
        Player[] lineup = team.getLineup();
        for(int player = 0; player < lineup.length; player++){
            Player curPlayer = lineup[player];
            row = genPlayerRow(curPlayer);
            table.addView(row);
        }
        genHitsRunsRow(lineup, table);
    }

    public TableRow genHeaders(){
        TableRow row = new TableRow(this);
        row.setBackgroundResource(R.drawable.cell_shape);
        TextView col1 = new TextView(this);
        col1.setText("#");
        //col1.setBackgroundResource(R.drawable.cell_shape);
        col1.setPadding(5, 5, 5, 5);
        col1.setGravity(Gravity.CENTER);
        row.addView(col1);

        TextView col2 = new TextView(this);
        col2.setText("     Player     ");
        //col2.setBackgroundResource(R.drawable.cell_shape);
        col2.setPadding(5, 5, 5, 5);
        col2.setGravity(Gravity.CENTER);
        row.addView(col2);

        TextView col3 = new TextView(this);
        col3.setText(" Pos ");
        //col3.setBackgroundResource(R.drawable.cell_shape);
        col3.setPadding(5, 5, 5, 5);
        col3.setGravity(Gravity.CENTER);
        row.addView(col3);


        for(int inning = 1; inning <= totalInnings; inning++){
            TextView col = new TextView(this);
            col.setText("  " + String.valueOf(inning) + "  ");
            col.setGravity(Gravity.CENTER);
            //col.setBackgroundResource(R.drawable.cell_shape);
            col.setPadding(5, 5, 5, 5);
            row.addView(col);
        }
        return row;
    }


    public TableRow genPlayerRow(Player player){
        TableRow row = new TableRow(this);
        TextView col1 = new TextView(this);
        col1.setText(String.valueOf(player.getNumber()));
        col1.setBackgroundResource(R.drawable.cell_shape);
        col1.setPadding(5, 5, 5, 5);
        col1.setGravity(Gravity.CENTER);
        row.addView(col1);

        TextView col2 = new TextView(this);
        col2.setText(player.getLastName());
        col2.setBackgroundResource(R.drawable.cell_shape);
        col2.setPadding(5, 5, 5, 5);
        col2.setGravity(Gravity.CENTER);
        row.addView(col2);

        TextView col3 = new TextView(this);
        col3.setText(player.getPosition());
        col3.setBackgroundResource(R.drawable.cell_shape);
        col3.setPadding(5, 5, 5, 5);
        col3.setGravity(Gravity.CENTER);
        row.addView(col3);

        ArrayList<AtBat> atBats = player.getPlayerAtBats();
        for(int curInning = 1; curInning <= totalInnings; curInning++){
            TextView col = new TextView(this);
            col.setText("   ");
            col.setBackgroundResource(R.drawable.cell_shape);
            for(AtBat bat : atBats){
                if(bat.getInning() == curInning){
                    col.setText(bat.getResult());
                }
            }
            col.setPadding(5,5,5,5);
            col.setGravity(Gravity.CENTER);
            row.addView(col);
        }
        return row;
    }

    public void genHitsRunsRow(Player[] lineup, TableLayout table){

    }

    private void setTotalInnings(Team team){
        Player[] lineup = team.getLineup();
        for (int player = 0; player < lineup.length; player++){
            Player curPlayer = lineup[player];
            ArrayList<AtBat> atBats = curPlayer.getPlayerAtBats();
            for(AtBat bat : atBats){
                if(bat.getInning() > totalInnings){
                    totalInnings = bat.getInning();
                }
            }
        }
    }

    private Scorecard genFakeScorecard(){
        Scorecard scorecard = new Scorecard();
        scorecard.setHomeTeam(genFakeHomeTeam());
        scorecard.setAwayTeam(genFakeAwayTeam());
        return scorecard;
    }

    private Team genFakeHomeTeam(){
        Team team = new Team("X", "Home", genFakePlayers(), true);
        team.setLineup(genFakePlayers());
        return team;
    }

    private Team genFakeAwayTeam(){
        Team team = new Team("Y", "Away", genFakePlayers(), false);
        team.setLineup(genFakePlayers());
        return team;
    }

    private Player[] genFakePlayers(){
        Player[] players = new Player[9];
        for(int i = 0; i < 9; i++){
            Player newPlayer = new Player("John", "Johnson", 5);
            newPlayer.setPlayerAtBats(genFakeAtBat());
            newPlayer.setPosition("SS");
            players[i] = newPlayer;
        }
        return players;
    }

    private ArrayList<AtBat> genFakeAtBat(){
        ArrayList<AtBat> atBats = new ArrayList<AtBat>();
        Random rdm = new Random();
        int rdmNum = rdm.nextInt(2);
        if(rdmNum == 0) {
            atBats.add(new AtBat(1, 2, 4, "W"));
            atBats.add(new AtBat(2, 2, 4, "2B"));
            atBats.add(new AtBat(3, 2, 4, "3B"));
            atBats.add(new AtBat(4, 2, 4, "HR"));
            atBats.add(new AtBat(5, 2, 4, "W"));
            atBats.add(new AtBat(6, 2, 4, "2B"));
            atBats.add(new AtBat(7, 2, 4, "1B"));
            atBats.add(new AtBat(8, 2, 4, "3B"));
            atBats.add(new AtBat(9, 2, 4, "HR"));
            return atBats;
        }
        else{
            atBats.add(new AtBat(1, 2, 4, "2B"));
            atBats.add(new AtBat(2, 2, 4, "1B"));
            atBats.add(new AtBat(3, 2, 4, "W"));
            atBats.add(new AtBat(4, 2, 4, "W"));
            atBats.add(new AtBat(5, 2, 4, "HR"));
            atBats.add(new AtBat(6, 2, 4, "2B"));
            atBats.add(new AtBat(7, 2, 4, "3B"));
            atBats.add(new AtBat(8, 2, 4, "W"));
            atBats.add(new AtBat(9, 2, 4, "W"));
            return atBats;
        }
    }
}
