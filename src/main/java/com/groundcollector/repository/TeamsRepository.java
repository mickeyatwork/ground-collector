package com.groundcollector.repository;

import com.groundcollector.model.Teams;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface TeamsRepository {

    public Teams findAll(Teams teams);

    public Teams updateGround(Teams teams, Integer integer);

    List<Teams> populateDropdown(List<Teams> dropdown);

    Teams findTeamById(Teams team, Integer teamId);

    Teams findTeamIdByName(Teams team, String teamName);

    Teams findTeamIdByName2(Teams team, String teamName, JdbcTemplate template);

    Teams findTeamById2(Teams team, Integer teamId, JdbcTemplate template);
}
