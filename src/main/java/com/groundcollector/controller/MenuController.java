package com.groundcollector.controller;

import com.groundcollector.model.Menu;
import com.groundcollector.model.TravelLog;
import com.groundcollector.model.Users;
import com.groundcollector.service.ActivityService;
import com.groundcollector.service.MenuService;
import com.groundcollector.service.UserService;
import com.groundcollector.utils.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class MenuController {


    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ApiConfig apiConfig;

    @GetMapping("menu")
    public String getMenu(@ModelAttribute("menu") TravelLog travelLog, Users users, Model model) {

        travelLog.setUserId(userService.findUserId(users).getId());
        menuService.findLatestEntry(travelLog);
        model.addAttribute("travelLogList", travelLog);

        Menu menuCount = new Menu(0);
        menuService.findLogCount(menuCount);
        System.out.println("Log count: " + menuCount.getLogCount());
        model.addAttribute("logCount", menuCount);
        activityService.findUniqueGroundsVisited(menuCount);
        model.addAttribute("uniqueVisits", menuCount.getUniqueVisitCount());

        ArrayList<Menu> mostVisited = new ArrayList<>();
        menuService.findMostVisited(mostVisited);
        model.addAttribute("mostVisited", mostVisited);

        /* TODO Checklist of all available grounds, based on the entries added (there's a select for distinct grounds by entry in GroundRepoImpl for 'geoLocationVisited' */
        /* TODO Select stadium in entries based on the club they're associated to (need to learn how to do cascading dropdowns via JS) */
        /* TODO Probably a bit more advanced, but be able to call specific matches from the API and then record them as an entry */
        /* TODO Team and/or Ground profiles (Likely links to other pages to host these) */
        /* TODO Countries visited (needs more grounds / teams from other countries adding for this though really */
        /* TODO If we get Geo location stuff one day, could do Cities visited. The API allows for Cities of where the grounds are located */
        /* TODO searchbar */
        /* TODO Longer term idea: Player info / profiles */

        return "menu";
    }

    @PostMapping("menu")
    public String addMenu(@Valid @ModelAttribute ("menu")
                                      Menu menu,
                                  BindingResult result) {

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "menu";
        }
        return "redirect:menu";
    }

}
