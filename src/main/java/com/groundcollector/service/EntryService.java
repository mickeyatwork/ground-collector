package com.groundcollector.service;

import com.groundcollector.model.Entry;
import com.groundcollector.model.TravelLog;

public interface EntryService {

        public Entry create (Entry entry);

        public Entry edit (Entry entry);

        public TravelLog findLogById(Entry entry, TravelLog travelLog);

        public Entry findEntryById(Entry entry);

        public Entry delete (Entry entry);
}
