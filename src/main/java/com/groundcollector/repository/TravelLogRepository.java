package com.groundcollector.repository;

import com.groundcollector.model.TravelLog;

import java.util.ArrayList;

public interface TravelLogRepository {

    ArrayList<TravelLog> findAllEntriesByUserID(ArrayList<TravelLog> travelLog);
}
