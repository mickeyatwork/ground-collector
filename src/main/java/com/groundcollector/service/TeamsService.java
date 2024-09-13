package com.groundcollector.service;

import com.groundcollector.model.Teams;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface TeamsService {

    public Teams findAll(Teams teams);

    public List<Teams> populateDropdown(List<Teams> dropdown);

    Teams findTeamById(Teams team, Integer teamId);

    Teams findTeamById2(Teams team, Integer teamId, JdbcTemplate template);
}
