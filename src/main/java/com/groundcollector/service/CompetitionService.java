package com.groundcollector.service;

import com.groundcollector.model.Competition;

import java.util.List;

public interface CompetitionService {

    List<Competition> populateDropdown(List<Competition> dropdown);

    List<Competition> populateDropdownLeaguesOnly (List<Competition> leagueDropdown);


}
