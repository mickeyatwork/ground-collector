package com.groundcollector.service;

import com.groundcollector.model.Users;
import com.groundcollector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findUserId(Users userId) {

        return userRepository.findUserId(userId);
    }
}
