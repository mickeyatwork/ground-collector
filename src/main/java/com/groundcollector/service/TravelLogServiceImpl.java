package com.groundcollector.service;

import com.groundcollector.model.TravelLog;
import com.groundcollector.repository.TravelLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TravelLogServiceImpl implements TravelLogService {

    @Autowired
    private TravelLogRepository travelLogRepository;

    @Override
    public ArrayList<TravelLog> findAllEntriesByUserID(ArrayList<TravelLog> travelLog) {
        return travelLogRepository.findAllEntriesByUserID((ArrayList<TravelLog>) travelLog);
    }
}
