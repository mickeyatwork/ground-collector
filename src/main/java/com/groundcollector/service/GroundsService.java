package com.groundcollector.service;

import com.groundcollector.model.Grounds;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public interface GroundsService {

    public Grounds findGroundsByName(String groundName);

    Grounds findGroundById(Grounds ground, Integer groundId);

    public List<Grounds> populateDropdown(List<Grounds> dropdown);

    public ArrayList<Grounds> geoLocationData(ArrayList<Grounds> geoLoc);

    public List<Grounds> geoLocationVisited(List<Grounds> geoLocVisited);

    Grounds findGroundIdByName(Grounds ground, String groundName, JdbcTemplate template);

    Grounds updateGroundDetails(Grounds ground, Integer groundId, JdbcTemplate template);

    Boolean groundDetailsAllMatch(Grounds ground, String groundName, JdbcTemplate template);

    Grounds updateGroundName(Grounds ground, String newGroundName, JdbcTemplate template);

    Grounds findGroundNameByDetails(Grounds ground, JdbcTemplate template);

    public List<Grounds> populateChecklist(List<Grounds> checklist, List<Grounds> visitedChecklist);

    public List<Grounds> populateVisitedChecklist(List<Grounds> visitedChecklist);

}
