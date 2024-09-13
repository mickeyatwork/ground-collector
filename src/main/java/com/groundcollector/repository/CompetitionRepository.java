package com.groundcollector.repository;

import com.groundcollector.model.Competition;

import java.util.List;

public interface CompetitionRepository {

    List<Competition> populateDropdown(List<Competition> dropdown);

    List<Competition> populateDropdownLeaguesOnly (List<Competition> leagueDropdown);

}
