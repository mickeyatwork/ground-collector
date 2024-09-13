package com.groundcollector.service;

import com.groundcollector.model.Menu;
import com.groundcollector.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Menu findLogCount(Menu menu) {
        return activityRepository.findLogCount(menu);
    }

    public Menu findUniqueGroundsVisited(Menu menu) {
        return activityRepository.findUniqueGroundsVisited(menu);
    }

}
