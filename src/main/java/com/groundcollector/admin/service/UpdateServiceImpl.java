package com.groundcollector.admin.service;

import com.groundcollector.admin.repository.UpdatesRepository;
import com.groundcollector.model.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceImpl implements UpdatesService {

    @Autowired
    public UpdatesRepository updatesRepository;

    @Override
    public Teams updateTeam(Teams team) {
        return updatesRepository.updateTeam(team);
    }

    @Override
    public Teams mapGround(Teams team, String groundName) {
        return updatesRepository.mapGround(team, groundName);
    }
}
