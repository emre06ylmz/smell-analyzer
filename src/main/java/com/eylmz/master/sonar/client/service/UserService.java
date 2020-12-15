package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.User;
import com.eylmz.master.sonar.client.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
