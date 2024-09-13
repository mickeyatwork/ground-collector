package com.groundcollector.service;

import com.groundcollector.model.Grounds;
import com.groundcollector.repository.GroundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroundsServiceImpl implements GroundsService {

    @Autowired
    private GroundsRepository groundsRepository;

    @Override
    public Grounds findGroundsByName( String groundName) {
        return groundsRepository.findGroundsByName( groundName);
    }

    @Override
    public Grounds findGroundById(Grounds ground, Integer groundId) {
        return groundsRepository.findGroundById(ground, groundId);
    }

    @Override
    public List<Grounds> populateDropdown(List<Grounds> dropdown) {
        return groundsRepository.populateDropdown(dropdown);
    }

    @Override
    public ArrayList<Grounds> geoLocationData(ArrayList<Grounds> geoLoc) {
        return groundsRepository.geoLocationData(geoLoc);
    }

    @Override
    public List<Grounds> geoLocationVisited(List<Grounds> geoLocVisited) {
        return groundsRepository.geoLocationVisited(geoLocVisited);
    }

    @Override
    public Grounds findGroundIdByName(Grounds ground, String groundName, JdbcTemplate template) {
        return groundsRepository.findGroundDetailsByName(ground, groundName, template);
    }

    @Override
    public Grounds updateGroundDetails(Grounds ground, Integer groundId, JdbcTemplate template) {
        return groundsRepository.updateGroundDetails(ground, groundId, template);
    }

    @Override
    public Boolean groundDetailsAllMatch(Grounds ground, String groundName, JdbcTemplate template) {
        return groundsRepository.groundDetailsAllMatch(ground, groundName, template);
    }

    @Override
    public Grounds updateGroundName(Grounds ground, String newGroundName, JdbcTemplate template) {
        return groundsRepository.updateGroundName(ground, newGroundName, template);
    }

    @Override
    public Grounds findGroundNameByDetails(Grounds ground, JdbcTemplate template) {
        return findGroundNameByDetails(ground, template);
    }

    @Override
    public List<Grounds> populateChecklist(List<Grounds> checklist, List<Grounds> visitedChecklist) {
        return groundsRepository.populateChecklist(checklist, visitedChecklist);
    }

    @Override
    public List<Grounds> populateVisitedChecklist(List<Grounds> visitedChecklist) {
        return groundsRepository.populateVisitedChecklist(visitedChecklist);
    }
}