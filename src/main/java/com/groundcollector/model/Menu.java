package com.groundcollector.model;

public class Menu {

    private long logCount;
    private int visitCount;
    private String visitedGround;
    private Long UniqueVisitCount;

    public Menu(long logCount) {
        this.logCount = logCount;
    }

    public Menu(int visitCount, String visitedGround) {
        this.visitCount = visitCount;
        this.visitedGround = visitedGround;
    }

    public Menu() {

    }

    public long getLogCount() {
        return logCount;
    }

    public void setLogCount(long logCount) {
        this.logCount = logCount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public String getVisitedGround() {
        return visitedGround;
    }

    public void setVisitedGround(String visitedGround) {
        this.visitedGround = visitedGround;
    }

    public Long getUniqueVisitCount() {
        return UniqueVisitCount;
    }

    public void setUniqueVisitCount(Long uniqueVisitCount) {
        UniqueVisitCount = uniqueVisitCount;
    }
}
