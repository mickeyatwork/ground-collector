package com.groundcollector.model;

public class TravelLog {

    private int id;
    private int userId;
    private String homeTeam;
    private String awayTeam;
    private String ground;
    private String competition;
    private String dateVisited;
    private String notes;
    private String score;
    private String homeBadge;
    private String awayBadge;

    public TravelLog(int id, int userId, String homeTeam, String awayTeam, String ground, String dateVisited, String notes, String score) {
        this.id = id;
        this.userId = userId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.ground = ground;
        this.dateVisited = dateVisited;
        this.notes = notes;
        this.score = score;
    }

    public TravelLog(int id, int userId, String homeTeam, String awayTeam, String ground, String dateVisited, String notes, String score, String homeBadge, String awayBadge) {
        this.id = id;
        this.userId = userId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.ground = ground;
        this.dateVisited = dateVisited;
        this.notes = notes;
        this.score = score;
        this.homeBadge = homeBadge;
        this.awayBadge = awayBadge;
    }

    public Object getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TravelLog(int userId) {
        this.userId = userId;
    }

    public TravelLog() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHomeBadge() {
        return homeBadge;
    }

    public void setHomeBadge(String homeBadge) {
        this.homeBadge = homeBadge;
    }

    public String getAwayBadge() {
        return awayBadge;
    }

    public void setAwayBadge(String awayBadge) {
        this.awayBadge = awayBadge;
    }

    @Override
    public String toString() {
        return "TravelLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", ground='" + ground + '\'' +
                ", competition='" + competition + '\'' +
                ", dateVisited='" + dateVisited + '\'' +
                ", notes='" + notes + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
