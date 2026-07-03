package com.groundcollector.admin.controller;

import com.groundcollector.admin.repository.GroundRepository;
import com.groundcollector.admin.repository.TeamRepository;
import com.groundcollector.model.Grounds;
import com.groundcollector.model.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import com.groundcollector.service.CompetitionService;
import com.groundcollector.model.Competition;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GroundRepository groundRepository;

    @Autowired
    private CompetitionService competitionService;

    @GetMapping
    public String getDashboard(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("grounds", groundRepository.findAll());
        
        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        model.addAttribute("competitionNames", competitionList);
        
        return "admin/dashboard";
    }

    @GetMapping("/editTeam/{id}")
    public String editTeam(@PathVariable("id") int id, Model model) {
        Optional<Teams> teamOpt = teamRepository.findById(id);
        if (teamOpt.isPresent()) {
            model.addAttribute("team", teamOpt.get());
            return "admin/editTeam";
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/editTeam")
    public String saveTeam(@ModelAttribute("team") Teams updatedTeam) {
        Optional<Teams> existingTeamOpt = teamRepository.findById(updatedTeam.getId());
        if (existingTeamOpt.isPresent()) {
            Teams existingTeam = existingTeamOpt.get();
            // Update fields manually
            existingTeam.setName(updatedTeam.getName());
            existingTeam.setCountry(updatedTeam.getCountry());
            existingTeam.setFounded(updatedTeam.getFounded());
            existingTeam.setLeagueId(updatedTeam.getLeagueId());
            existingTeam.setGroundId(updatedTeam.getGroundId());
            existingTeam.setLogo(updatedTeam.getLogo());
            
            teamRepository.save(existingTeam);
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/editGround/{id}")
    public String editGround(@PathVariable("id") int id, Model model) {
        Optional<Grounds> groundOpt = groundRepository.findById(id);
        if (groundOpt.isPresent()) {
            model.addAttribute("ground", groundOpt.get());
            return "admin/editGround";
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/editGround")
    public String saveGround(@ModelAttribute("ground") Grounds updatedGround) {
        Optional<Grounds> existingGroundOpt = groundRepository.findById(updatedGround.getId());
        if (existingGroundOpt.isPresent()) {
            Grounds existingGround = existingGroundOpt.get();
            // Update fields manually
            existingGround.setName(updatedGround.getName());
            existingGround.setAliases(updatedGround.getAliases());
            existingGround.setCapacity(updatedGround.getCapacity());
            existingGround.setCity(updatedGround.getCity());
            existingGround.setBuilt(updatedGround.getBuilt());
            existingGround.setImage(updatedGround.getImage());
            existingGround.setActive(updatedGround.getActive());
            existingGround.setLat(updatedGround.getLat());
            existingGround.setLng(updatedGround.getLng());
            
            groundRepository.save(existingGround);
        }
        return "redirect:/admin/dashboard";
    }
}
