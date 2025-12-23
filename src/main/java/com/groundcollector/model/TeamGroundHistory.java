package com.groundcollector.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team_ground_history")
public class TeamGroundHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "ground_id")
    private Integer groundId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "reason")
    private String reason; // Can be 'NAME_CHANGE', 'RELOCATION', etc.

    public TeamGroundHistory() {
    }

    public TeamGroundHistory(Integer teamId, Integer groundId, Date startDate) {
        this.teamId = teamId;
        this.groundId = groundId;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGroundId() {
        return groundId;
    }

    public void setGroundId(Integer groundId) {
        this.groundId = groundId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
