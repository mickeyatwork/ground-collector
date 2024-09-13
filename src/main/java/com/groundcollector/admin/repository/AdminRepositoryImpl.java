package com.groundcollector.admin.repository;

import com.groundcollector.admin.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Override
    public Admin getLeagueId(Admin admin) {

        admin.getLeagueId();

        return admin;
    }

    @Override
    public Admin getTeamName(Admin admin) {

        admin.getTeamName();

        return admin;
    }

    @Override
    public Admin getUpdateFieldName(Admin admin) {

        admin.getUpdateFieldName();

        return admin;
    }
}
