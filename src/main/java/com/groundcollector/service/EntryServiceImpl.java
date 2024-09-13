package com.groundcollector.service;

import com.groundcollector.model.Entry;
import com.groundcollector.model.TravelLog;
import com.groundcollector.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry create(Entry entry) {
        return entryRepository.create(entry);
    }

    @Override
    public Entry edit(Entry entry) {
        return entryRepository.edit(entry);
    }

    @Override
    public TravelLog findLogById(Entry entry, TravelLog travelLog) {
        return entryRepository.findLogById(entry, travelLog);
    }

    @Override
    public Entry findEntryById(Entry entry) {
        return entryRepository.findEntryById(entry);
    }

    @Override
    public Entry delete(Entry entry) {
        return entryRepository.delete(entry);
    }

}
