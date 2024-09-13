package com.groundcollector.repository;

import com.groundcollector.model.Menu;
import com.groundcollector.model.TravelLog;

import java.util.ArrayList;

public interface MenuRepository {

    TravelLog findLatestEntry (TravelLog travelLog);

    Menu findLogCount (Menu menu);

    ArrayList<Menu> findMostVisited (ArrayList<Menu> menu);
}
