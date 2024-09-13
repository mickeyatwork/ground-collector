package com.groundcollector.repository;

import com.groundcollector.model.Menu;

public interface ActivityRepository {

    Menu findLogCount (Menu menu);

    Menu findUniqueGroundsVisited (Menu menu);


}
