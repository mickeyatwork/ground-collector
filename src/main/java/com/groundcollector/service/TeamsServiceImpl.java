package com.groundcollector.service;

import com.groundcollector.model.Teams;
import com.groundcollector.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService{


    @Autowired
    private TeamsRepository teamsRepository;

    @Override
    public Teams findAll(Teams teams){
        return teamsRepository.findAll(teams);
    }

    @Override
    public List<Teams> populateDropdown(List<Teams> dropdown) {
        return teamsRepository.populateDropdown(dropdown);
    }

    @Override
    public Teams findTeamById(Teams team, Integer teamId) {
        return teamsRepository.findTeamById(team, teamId);
    }

    @Override
    public Teams findTeamById2(Teams team, Integer teamId, JdbcTemplate template) {
        return teamsRepository.findTeamById2(team, teamId, template);
    }
}
