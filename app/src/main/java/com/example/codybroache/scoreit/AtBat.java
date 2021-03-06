package com.example.codybroache.scoreit;

/**
 * Created by Cody Broache on 11/19/2015.
 */
public class AtBat {
    int inning;
    int balls;
    int strikes;
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getStikes() {
        return strikes;
    }

    public void setStikes(int strikes) {
        this.strikes = strikes;
    }

    public AtBat(int inning, int balls, int strikes) {

        this.inning = inning;
        this.balls = balls;
        this.strikes = strikes;
    }

    public AtBat(int inning, int balls, int strikes, String result) {

        this.inning = inning;
        this.balls = balls;
        this.strikes = strikes;
        this.result = result;
    }
}
