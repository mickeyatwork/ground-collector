package com.groundcollector.admin.repository;

import com.groundcollector.model.Teams;

public interface UpdatesRepository {

    public Teams updateTeam(Teams team);

    public Teams mapGround(Teams team, String groundName);
}
