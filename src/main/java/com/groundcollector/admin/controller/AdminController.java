package com.groundcollector.admin.controller;

import com.groundcollector.admin.api.*;
import com.groundcollector.admin.model.Admin;
import com.groundcollector.admin.repository.AdminRepository;
import com.groundcollector.admin.repository.GroundRepository;
import com.groundcollector.admin.repository.TeamRepository;
import com.groundcollector.admin.repository.UpdatesRepository;
import com.groundcollector.model.Competition;
import com.groundcollector.model.Entry;
import com.groundcollector.model.Grounds;
import com.groundcollector.model.Teams;
import com.groundcollector.repository.TeamsRepository;
import com.groundcollector.service.CompetitionService;
import com.groundcollector.service.GroundsService;
import com.groundcollector.service.TeamsService;
import com.groundcollector.utils.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class AdminController {

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GroundRepository groundRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UpdatesRepository updatesRepository;

    @Autowired
    private GroundsService groundsService;

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public ApiConfig apiConfig;

    @GetMapping("admin")
    public String getAdmin(@ModelAttribute("admin") Admin admin) {
        return "admin/admin";
    }

    @GetMapping("teamsUpdate")
    public String getTeamsMenu(@ModelAttribute("adminTeams") Admin admin, Model model) {

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        model.addAttribute("competitionNames", competitionList);

        return "admin/teamsUpdate";
    }

    @GetMapping("groundsUpdate")
    public String getGroundsMenu(@ModelAttribute("adminGrounds") Admin admin, Model model) {

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        model.addAttribute("competitionNames", competitionList);

        return "admin/groundsUpdate";
    }

    @GetMapping("createGround")
    public String getCreateGround(@ModelAttribute("createGround") Admin admin, Model model) throws IOException, InterruptedException {

        CreateIndividualGround parser = new CreateIndividualGround();
        parser.addGround(apiConfig);
        //parser.setSearchTerm(admin.getSearchTerm());

        return "admin/createGround";
    }

    @GetMapping("mapGrounds")
    public String getGroundMappingMenu(@ModelAttribute("mapGrounds") Admin admin, Model model) {

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        model.addAttribute("competitionNames", competitionList);

        return "admin/mapGrounds";
    }

    @GetMapping("individualTeamUpdate")
    public String getTeamUpdateMenu(@ModelAttribute("teamUpdate") Admin admin, Model model) {

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        System.out.println("competitionList size: " + competitionList.size());
        model.addAttribute("competitionNames", competitionList);

        ArrayList<Grounds> groundsList = new ArrayList<>();
        groundsService.populateDropdown(groundsList);
        System.out.println("groundsList size: " + groundsList.size());
        model.addAttribute("groundsName", groundsList);

        return "admin/individualTeamUpdate";
    }

    @GetMapping("updateField")
    public String getUpdateField(@ModelAttribute("updateField") Teams team, Model model){

        ArrayList<Teams> teamsList = new ArrayList<>();
        teamsService.populateDropdown(teamsList);
        model.addAttribute("teamName", teamsList);


        return "admin/updateField";
    }

    @GetMapping("updateTeamFields")
    public String getUpdateTeamFields(@ModelAttribute("updateTeamFields") Entry entry, Teams team, Model model, HttpServletRequest request) {

        System.out.println("Made it to team update page!!!");
        System.out.println("Model attribute: " + model.getAttribute("updateField"));
        // team.setId(model.getAttribute("updateField"));
        System.out.println(team.getId());

        teamsService.findTeamById(team, team.getId());
        model.addAttribute("teamCurrentDetails", team);

        ArrayList<Grounds> groundsList = new ArrayList<>();
        groundsService.populateDropdown(groundsList);
        model.addAttribute("groundsName", groundsList);

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        model.addAttribute("competitionNames", competitionList);


        return "admin/updateTeamFields";
    }

    @GetMapping("fieldUpdate")
    public String getFieldMenu(@ModelAttribute("adminField") Admin admin, Model model) {

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdownLeaguesOnly(competitionList);
        model.addAttribute("competitionNames", competitionList);

        return "admin/fieldUpdate";
    }


    @PostMapping("admin")
    public String addAdmin(@Valid @ModelAttribute ("admin")
                                  Admin admin,
                          BindingResult result) {


        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/admin";
        }

        return "admin/admin";
    }

    @PostMapping("teamsUpdate")
    public String addTeams(@Valid @ModelAttribute ("adminTeams")
                                   Admin admin,
                           BindingResult result) {

        admin = adminRepository.getLeagueId(admin);
        System.out.println("League ID selected: " + admin.getLeagueId());

        TeamsApiJsonParser parser = new TeamsApiJsonParser();
        parser.setLeagueIdForApi(admin.getLeagueId());

        //This adds all selected teams to the DB:
        parser.insertTeam(teamRepository, apiConfig);

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/teamsUpdate";
        }

        return "admin/admin";
    }

    @PostMapping("groundsUpdate")
    public String addGrounds(@Valid @ModelAttribute ("adminGrounds")
                                   Admin admin,
                           BindingResult result) {

        GroundsApiJsonParser parser = new GroundsApiJsonParser();
        parser.setLeagueIdForApi(admin.getLeagueId());

        JdbcTemplate template = new JdbcTemplate(dataSource);

        //This adds all selected grounds to the DB:
        parser.insertGround(groundRepository, template, apiConfig);


        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/groundsUpdate";
        }

        return "admin/admin";
    }
    @PostMapping("createGround")
    public String createGround(@Valid @ModelAttribute ("createGround")
                                   Admin admin,
                           BindingResult result) throws IOException, InterruptedException {

        CreateIndividualGround parser = new CreateIndividualGround();
        parser.setSearchTerm(admin.getSearchTerm());

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/createGround";
        }

        return "admin/admin";
    }

    @PostMapping("mapGrounds")
    public String mapGrounds(@Valid @ModelAttribute ("mapGrounds")
                                   Admin admin,
                           BindingResult result) {

        System.out.println("admin value: " + admin.getLeagueId());
        admin = adminRepository.getLeagueId(admin);
        System.out.println("League ID selected: " + admin.getLeagueId());

        GroundToTeamMapper parser = new GroundToTeamMapper();
        parser.setLeagueIdForApi(admin.getLeagueId());

        // This will map teams to their home grounds
        parser.matchGroundsToTeams(updatesRepository, apiConfig);

        if (result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/teamsUpdate";
        }

        return "admin/admin";
    }

    @PostMapping("individualTeamUpdate")
    public String updateTeam(@Valid @ModelAttribute ("teamUpdate")
                                     Admin admin,
                             BindingResult result) {

        admin = adminRepository.getTeamName(admin);

        admin.setTeamName(admin.getTeamName().replaceAll("\\s+", "%20"));
        System.out.println("Team search term: " + admin.getTeamName());
        System.out.println("Submitted elements - League ID:" + admin.getLeagueId() + ", Ground ID:" + admin.getGroundId() );

        IndividualTeamUpdateParser parser = new IndividualTeamUpdateParser();

        parser.setTeamNameForApi(admin.getTeamName());
        parser.setLeagueId(admin.getLeagueId());
        parser.setGroundId(admin.getGroundId());

        //This adds all selected teams to the DB:
        parser.updateTeam(updatesRepository, apiConfig);

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/teamsUpdate";
        }

        return "admin/admin";
    }

    @PostMapping("updateField")
    public String updateField(@Valid @ModelAttribute ("updateField")
                                     Admin admin,
                             BindingResult result) {

        Teams team = new Teams();
        team.setId(admin.getTeamId());
        teamsRepository.findTeamById(team, team.getId());

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/admin";
        }

        System.out.println("Update team data for: " + team);

        return "admin/updateTeamFields";
        //return "admin/updateTeamFields?teamId=" + team.getId();
    }

    @PostMapping("fieldUpdate")
    public String addField(@Valid @ModelAttribute ("adminField")
                                   Admin admin,
                           BindingResult result) {

        FieldUpdateParser parser = new FieldUpdateParser();
        admin = adminRepository.getLeagueId(admin);
        System.out.println("League ID selected: " + admin.getLeagueId());

        parser.setLeagueIdForApi(admin.getLeagueId());
        //admin = adminRepository.getUpdateFieldName(admin);
        parser.setUpdateFieldName(admin.getUpdateFieldName());

        JdbcTemplate template = new JdbcTemplate(dataSource);
        //This adds all selected teams to the DB:
        parser.insertTeam(teamRepository, template, apiConfig);

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/teamsUpdate";
        }

        return "admin/admin";
    }

    /* TODO Check for any stadium name changes (Some sort of select where the the ground name doesn't exist for a team.
        Could Use the grounds mapping queries as a reference point?)*/
    /* TODO Update specific columns for teams / grounds, based on input criteria (text input / dropdown / both)? */

}
