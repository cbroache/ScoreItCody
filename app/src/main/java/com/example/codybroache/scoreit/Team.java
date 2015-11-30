package com.example.codybroache.scoreit;

/**
 * Created by Brandon on 11/3/2015.
 */
public class Team {
    private String sLocation;
    private String sNickname;
    private Player[] aRoster = new Player[25];
    private Player[] aLineup = new Player[9];
    private Player pPitcher;
    private boolean bHome;

    private int spotInOrder = 0;
    private int score;

    public Team(String location, String nickname, Player[] roster, boolean home) {
        this.sLocation = location;
        this.sNickname = nickname;
        this.aRoster = roster;
        this.bHome = home;
    }

    public Team(Team team) {
        this.sLocation = team.getLocation();
        this.sNickname = team.getNickname();
        this.aRoster = team.getRoster();
        this.bHome = team.getHome();
    }

    public String getLocation() {
        return sLocation;
    }

    public String getNickname() {
        return sNickname;
    }

    public Player[] getRoster() {
        return aRoster;
    }

    public Player[] getLineup() {
        return aLineup;
    }

    public void setLineup(Player[] lineup) {
        this.aLineup = lineup;
    }

    public Player getPitcher() {
        return pPitcher;
    }

    public void setPitcher(Player pitcher) {
        this.pPitcher = pitcher;
    }

    public boolean getHome() {
        return bHome;
    }

    public int getSpotInOrder() {
        return spotInOrder;
    }

    public void setSpotInOrder() {
        if (spotInOrder == 8) {
            spotInOrder = 0;
        } else {
            spotInOrder++;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
