package com.groundcollector.service;

import com.groundcollector.model.Menu;
import com.groundcollector.model.TravelLog;
import com.groundcollector.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public TravelLog findLatestEntry(TravelLog travelLog) {
        return menuRepository.findLatestEntry(travelLog);
    }

    @Override
    public Menu findLogCount(Menu menu) {
        return menuRepository.findLogCount(menu);
    }

    @Override
    public ArrayList<Menu> findMostVisited(ArrayList<Menu> menu) {
        return menuRepository.findMostVisited((ArrayList<Menu>) menu);
    }
}
