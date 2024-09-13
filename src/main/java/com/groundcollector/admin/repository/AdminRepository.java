package com.groundcollector.admin.repository;

import com.groundcollector.admin.model.Admin;

public interface AdminRepository {

    public Admin getLeagueId (Admin admin);

    public Admin getTeamName (Admin admin);

    public Admin getUpdateFieldName(Admin admin);

}
