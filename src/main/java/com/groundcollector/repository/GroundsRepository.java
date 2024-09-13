package com.groundcollector.repository;

import com.groundcollector.model.Grounds;
import com.groundcollector.model.Teams;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public interface GroundsRepository  {

    public Grounds findIdByName(Teams teams, String str);

    public Grounds findGroundsByName(String groundName);

    Grounds findGroundById(Grounds ground, Integer groundId);

    List<Grounds> populateDropdown(List<Grounds> dropdown);

    ArrayList<Grounds> geoLocationData(ArrayList<Grounds> geoLoc);

    List<Grounds> geoLocationVisited(List<Grounds> geoLocVisited);

    Grounds findGroundDetailsByName(Grounds ground, String groundName, JdbcTemplate template);

    boolean existsByName(String name);

    Grounds updateGroundDetails(Grounds ground, Integer groundId, JdbcTemplate template);

    Boolean groundDetailsAllMatch(Grounds ground, String groundName, JdbcTemplate template);

    Grounds updateGroundName(Grounds ground, String newGroundName, JdbcTemplate template);

    Grounds findGroundNameByDetails(Grounds ground, JdbcTemplate template);

    List<Grounds> populateChecklist(List<Grounds> checklist, List<Grounds> visitedChecklist);

    List<Grounds> populateVisitedChecklist(List<Grounds> visitedChecklist);

}
