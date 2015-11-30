package com.example.codybroache.scoreit;

/**
 * Created by Brandon on 11/3/2015.
 */
public class Player {
    // Player information
    private String firstName;
    private String lastName;
    private String position;
    private int number;
    private boolean usedInGame;

    // Player batting statistics
    private int plateAppearances;
    private int atBats;
    private int singles;
    private int doubles;
    private int triples;
    private int homeruns;
    private int basesOnBalls;
    private int ks;
    private int hbps;
    private int errorsReachedOn;
    private int sacrificeFlies;
    private int rbis;
    private int doublePlays;

    // Player running statistics
    private int runs;
    private int steals;
    private int caughtStealings;

    // Player defensive statistics
    private int errorsCommitted;
    private int putOuts;
    private int assists;

    public Player(String firstName, String lastName, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public String toString() {
        return this.firstName + " " + this.lastName + ":" + this.number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getUsedInGame() {
        return usedInGame;
    }

    public void setUsedInGame(boolean usedInGame) {
        this.usedInGame = usedInGame;
    }

    public int getPlateAppearances() {
        return plateAppearances;
    }

    public void setPlateAppearances(int plateAppearances) {
        this.plateAppearances = plateAppearances;
    }

    public int getAtBats() {
        return atBats;
    }

    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    public int getSingles() {
        return singles;
    }

    public void setSingles(int singles) {
        this.singles = singles;
    }

    public int getDoubles() {
        return doubles;
    }

    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    public int getTriples() {
        return triples;
    }

    public void setTriples(int triples) {
        this.triples = triples;
    }

    public int getHomeruns() {
        return homeruns;
    }

    public void setHomeruns(int homeruns) {
        this.homeruns = homeruns;
    }

    public int getBasesOnBalls() {
        return basesOnBalls;
    }

    public void setBasesOnBalls(int basesOnBalls) {
        this.basesOnBalls = basesOnBalls;
    }

    public int getKs() {
        return ks;
    }

    public void setKs(int ks) {
        this.ks = ks;
    }

    public int getHbps() {
        return hbps;
    }

    public void setHbps(int hbps) {
        this.hbps = hbps;
    }

    public int getErrorsReachedOn() {
        return errorsReachedOn;
    }

    public void setErrorsReachedOn(int errorsReachedOn) {
        this.errorsReachedOn = errorsReachedOn;
    }

    public int getSacrificeFlies() {
        return sacrificeFlies;
    }

    public void setSacrificeFlies(int sacrificeFlies) {
        this.sacrificeFlies = sacrificeFlies;
    }

    public int getRbis() {
        return rbis;
    }

    public void setRbis(int rbis) {
        this.rbis = rbis;
    }

    public int getDoublePlays() {
        return doublePlays;
    }

    public void setDoublePlays(int doublePlays) {
        this.doublePlays = doublePlays;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getCaughtStealings() {
        return caughtStealings;
    }

    public void setCaughtStealings(int caughtStealings) {
        this.caughtStealings = caughtStealings;
    }

    public int getErrorsCommitted() {
        return errorsCommitted;
    }

    public void setErrorsCommitted(int errorsCommitted) {
        this.errorsCommitted = errorsCommitted;
    }

    public int getPutOuts() {
        return putOuts;
    }

    public void setPutOuts(int putOuts) {
        this.putOuts = putOuts;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
}
