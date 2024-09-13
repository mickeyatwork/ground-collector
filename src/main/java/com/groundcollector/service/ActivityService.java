package com.groundcollector.service;

import com.groundcollector.model.Menu;

public interface ActivityService {

    Menu findLogCount (Menu menu);

    Menu findUniqueGroundsVisited(Menu menu);
}
