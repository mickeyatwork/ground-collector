package com.groundcollector.controller;

import com.groundcollector.model.Grounds;
import com.groundcollector.model.TravelLog;
import com.groundcollector.model.Users;
import com.groundcollector.service.GroundsService;
import com.groundcollector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.sql.DataSource;
import java.util.ArrayList;

@Controller
public class ChecklistController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private GroundsService groundsService;

    @Autowired
    private UserService userService;

    @GetMapping("groundChecklist")
    public String getGroundChecklist(@ModelAttribute("groundChecklist") Users users, Model model) {

        // Now get grounds that the user has been to
        ArrayList<Grounds> visitedGrounds = new ArrayList<>();
        groundsService.populateVisitedChecklist(visitedGrounds);
        model.addAttribute("visitedGrounds", visitedGrounds);
        System.out.println("Visited grounds total: " + visitedGrounds.size());

        // Grab all grounds and put them in an array
        ArrayList<Grounds> allGrounds = new ArrayList<>();
        groundsService.populateChecklist(allGrounds, visitedGrounds);
        model.addAttribute("allGrounds", allGrounds);
        System.out.println("All available grounds total: " + allGrounds.size());

        return "groundChecklist";
    }
}
