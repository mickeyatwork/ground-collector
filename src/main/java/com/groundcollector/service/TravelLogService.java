package com.groundcollector.service;

import com.groundcollector.model.TravelLog;

import java.util.ArrayList;

public interface TravelLogService {

    ArrayList<TravelLog> findAllEntriesByUserID(ArrayList<TravelLog> travelLog);

}
