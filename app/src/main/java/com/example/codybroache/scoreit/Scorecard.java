package com.example.codybroache.scoreit;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Cody Broache on 11/15/2015.
 */
public class Scorecard {
    ArrayList<Player> homePlayers;
    ArrayList<Player> awayPlayers;
    String homeTeam;
    String awayTeam;
    Date date;

    public Scorecard(String homeTeam, String awayTeam, Date date) {
        this.homePlayers = new ArrayList<Player>();
        this.awayPlayers = new ArrayList<Player>();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
    }

    public ArrayList<Player> getHomePlayers() {

        return homePlayers;
    }

    public void setHomePlayers(ArrayList<Player> homePlayers) {
        this.homePlayers = homePlayers;
    }

    public ArrayList<Player> getAwayPlayers() {
        return awayPlayers;
    }

    public void setAwayPlayers(ArrayList<Player> awayPlayers) {
        this.awayPlayers = awayPlayers;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
