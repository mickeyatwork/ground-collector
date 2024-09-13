package com.groundcollector.controller;

import com.groundcollector.model.TravelLog;
import com.groundcollector.model.Users;
import com.groundcollector.service.TravelLogService;
import com.groundcollector.service.UserService;
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
public class TravelLogController {

    @Autowired
    private UserService userService;

    @Autowired
    private TravelLogService travelLogService;

    @GetMapping("travelLog")
    public String getTravelLog(@ModelAttribute("travelLog") Users users, Model model) {

        //TravelLog travelLog = new TravelLog(userService.findUserId(users).getId());
        ArrayList<TravelLog> travelLogList = new ArrayList<>();
        travelLogService.findAllEntriesByUserID(travelLogList);
        System.out.println("travelLog size: " + travelLogList.size());
        model.addAttribute("travelLogList", travelLogList);

        return "travelLog";
    }

    @PostMapping("travelLog")
    public String addTravelLog(@Valid @ModelAttribute ("travelLog")
                                           TravelLog travelLog,
                          BindingResult result) {

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "travelLog";
        }
        return "travelLog";
    }
}
