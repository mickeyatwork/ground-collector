package com.groundcollector.admin.service;

import com.groundcollector.model.Teams;

public interface UpdatesService {

    public Teams updateTeam(Teams team);

    public Teams mapGround(Teams team, String groundName);

}
