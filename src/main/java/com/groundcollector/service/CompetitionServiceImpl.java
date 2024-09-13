package com.groundcollector.service;

import com.groundcollector.model.Competition;
import com.groundcollector.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public List<Competition> populateDropdown(List<Competition> dropdown) {
        return competitionRepository.populateDropdown(dropdown);
    }

    @Override
    public List<Competition> populateDropdownLeaguesOnly(List<Competition> leagueDropdown) {
        return competitionRepository.populateDropdownLeaguesOnly(leagueDropdown);
    }
}
