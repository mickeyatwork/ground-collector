package com.groundcollector.controller;

import com.groundcollector.model.Grounds;
import com.groundcollector.model.Users;
import com.groundcollector.service.GroundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@Controller
public class MapController {

    @Autowired
    private GroundsService groundsService;

    @Value("${maps.key}")
    private String mapsKey;

    @GetMapping("map")
    public String getMap(@ModelAttribute("map") Users user, Model model) {

        model.addAttribute("mapsKey", mapsKey);

        ArrayList<Grounds> geoLocationList = new ArrayList<>();
        groundsService.geoLocationData(geoLocationList);
        System.out.println("GeoLocationData retrieved: " + geoLocationList.size());
        model.addAttribute("geoLocData", geoLocationList);

        ArrayList<Grounds> geoLocationVisitedList = new ArrayList<>();
        groundsService.geoLocationVisited(geoLocationVisitedList);
        System.out.println("GeoLocationVisited retrieved: " + geoLocationVisitedList.size());
        model.addAttribute("geoLocVisited", geoLocationVisitedList);

        return "map";
    }


}
