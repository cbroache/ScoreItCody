package com.example.codybroache.scoreit;

import java.util.Date;

/**
 * Created by Cody Broache on 11/15/2015.
 */
public class Scorecard {
    private String homeTeam;
    private String awayTeam;
    private Date date;

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

    public Scorecard(String homeTeam, String awayTeam, Date date) {

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
    }
}
