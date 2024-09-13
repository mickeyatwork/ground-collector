package com.groundcollector.controller;

import com.groundcollector.model.*;
import com.groundcollector.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamsService teamsService;

    @Autowired
    private GroundsService groundsService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private DataSource dataSource;

    @GetMapping("entryTesting")
    public String  getNewTestEntry(@ModelAttribute("entryTesting") Entry entry, Teams teams, Model model) {

        ArrayList<Teams> teamsList = new ArrayList<>();
        teamsService.populateDropdown(teamsList);
        System.out.println("teamList size: " + teamsList.size());
        model.addAttribute("teamName", teamsList);

        ArrayList<Grounds> groundsList = new ArrayList<>();
        groundsService.populateDropdown(groundsList);
        System.out.println("groundsList size: " + groundsList.size());
        model.addAttribute("groundsName", groundsList);

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdown(competitionList);
        System.out.println("competitionList size: " + competitionList.size());
        model.addAttribute("competitionNames", competitionList);

        return "entryTesting";
    }

    @GetMapping("newEntry")
    public String  getNewEntry(@ModelAttribute("newEntry") Entry entry, Teams teams, Model model) {

        ArrayList<Teams> teamsList = new ArrayList<>();
        teamsService.populateDropdown(teamsList);
        System.out.println("teamList size: " + teamsList.size());
        model.addAttribute("teamName", teamsList);

        ArrayList<Grounds> groundsList = new ArrayList<>();
        groundsService.populateDropdown(groundsList);
        System.out.println("groundsList size: " + groundsList.size());
        model.addAttribute("groundsName", groundsList);

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdown(competitionList);
        System.out.println("competitionList size: " + competitionList.size());
        model.addAttribute("competitionNames", competitionList);

        return "newEntry";
    }

    @GetMapping("editEntry")
    public String getEditEntry(@ModelAttribute("editEntry") Entry entry, Teams teams, Model model, TravelLog travelLog, HttpServletRequest request) {

        int entryId;
        entryId = Integer.parseInt(request.getParameter("entId"));
        entry.setId(entryId);

        entryService.findLogById(entry, travelLog);
        entryService.findEntryById(entry);
        //System.out.println("Entry ID: " + travelLog.getId());
        model.addAttribute("editingLog", travelLog);
        model.addAttribute("editingEntry", entry);

        String[] splitString = travelLog.getScore().split(" - ");
        model.addAttribute("editHomeScore", splitString[0]);
        model.addAttribute("editAwayScore", splitString[1]);

        ArrayList<Teams> teamsList = new ArrayList<>();
        teamsService.populateDropdown(teamsList);
        //System.out.println("teamList size: " + teamsList.size());
        model.addAttribute("teamName", teamsList);

        ArrayList<Grounds> groundsList = new ArrayList<>();
        groundsService.populateDropdown(groundsList);
        //System.out.println("groundsList size: " + groundsList.size());
        model.addAttribute("groundsName", groundsList);

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdown(competitionList);
        //System.out.println("competitionList size: " + competitionList.size());
        model.addAttribute("competitionNames", competitionList);



        return "editEntry";
    }

    @GetMapping("deleteEntry")
    public String getDeleteEntry(@ModelAttribute("deleteEntry") Entry entry, Teams teams, Model model, TravelLog travelLog, HttpServletRequest request) {

        int entryId;
        entryId = Integer.parseInt(request.getParameter("entId"));
        entry.setId(entryId);

        entryService.findLogById(entry, travelLog);
        entryService.findEntryById(entry);
        //System.out.println("Entry ID: " + travelLog.getId());
        model.addAttribute("deletingLog", travelLog);
        model.addAttribute("deletingEntry", entry);

        String[] splitString = travelLog.getScore().split(" - ");
        model.addAttribute("deleteHomeScore", splitString[0]);
        model.addAttribute("deleteAwayScore", splitString[1]);

        ArrayList<Teams> teamsList = new ArrayList<>();
        teamsService.populateDropdown(teamsList);
        //System.out.println("teamList size: " + teamsList.size());
        model.addAttribute("teamName", teamsList);

        ArrayList<Grounds> groundsList = new ArrayList<>();
        groundsService.populateDropdown(groundsList);
        //System.out.println("groundsList size: " + groundsList.size());
        model.addAttribute("groundsName", groundsList);

        ArrayList<Competition> competitionList = new ArrayList<>();
        competitionService.populateDropdown(competitionList);
        //System.out.println("competitionList size: " + competitionList.size());
        model.addAttribute("competitionNames", competitionList);

        return "deleteEntry";
    }

    @PostMapping("newEntry")
    public String addNewEntry(@Valid @ModelAttribute ("newEntry")
                                      Entry entry,
                              Users users,
                              BindingResult result) {

        entry.setUserId(userService.findUserId(users).getId());
        entry.setScore(entry.getHomeScore() + " - " + entry.getAwayScore());
        entry = entryService.create(entry);

        return "redirect:newEntry";
    }

    @PostMapping("editEntry")
    public String addEditEntry(@Valid @ModelAttribute ("editEntry")
                                          Entry entry,
                                            Users users,
                               HttpServletRequest request,
                                  BindingResult result) {

        entry.setId(Integer.parseInt(request.getParameter("entId")));
        entry.setUserId(userService.findUserId(users).getId());
        entry.setScore(entry.getHomeScore() + " - " + entry.getAwayScore());
        entry = entryService.edit(entry);

        return "redirect:travelLog";
    }

    @PostMapping("entryTesting")
    public String  addNewTestEntry(@Valid @ModelAttribute("entryTesting")
                Entry entry,
                Users users,
                HttpServletRequest request,
                BindingResult result) {

            entry.setUserId(userService.findUserId(users).getId());
            entry.setScore(entry.getHomeScore() + " - " + entry.getAwayScore());
            entry = entryService.create(entry);

            return "redirect:entryTesting";
        }

    @PostMapping("deleteEntry")
    public String addDeleteEntry(@Valid @ModelAttribute ("deleteEntry")
                                       Entry entry,
                               Users users,
                               HttpServletRequest request,
                               BindingResult result) {

        entry.setId(Integer.parseInt(request.getParameter("entId")));
        entry.setUserId(userService.findUserId(users).getId());
        entry = entryService.delete(entry);

        return "redirect:travelLog";
    }
}
