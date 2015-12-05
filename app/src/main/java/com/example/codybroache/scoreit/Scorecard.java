package com.example.codybroache.scoreit;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Cody Broache on 11/15/2015.
 */
public class Scorecard {
    Team homeTeam;
    Team awayTeam;
    Date date;

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Scorecard(Team homeTeam, Team awayTeam, Date date) {

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
    }

    public Scorecard(Team homeTeam, Team awayTeam) {

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = new Date();
    }

    public Scorecard() {
        this.homeTeam = null;
        this.awayTeam = null;
        this.date = new Date();
    }
}
