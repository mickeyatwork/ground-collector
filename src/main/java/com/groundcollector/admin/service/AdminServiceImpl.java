package com.groundcollector.admin.service;

import com.groundcollector.admin.model.Admin;
import com.groundcollector.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin getLeagueId(Admin admin) {
        return adminRepository.getLeagueId(admin);
    }

    @Override
    public Admin getTeamName(Admin admin) {
        return adminRepository.getTeamName(admin);
    }

    @Override
    public Admin getUpdateFieldName(Admin admin) {
        return adminRepository.getUpdateFieldName(admin);
    }
}
