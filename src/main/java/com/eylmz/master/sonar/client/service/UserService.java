package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.controller.GithubController;
import com.eylmz.master.sonar.client.dto.github.User;
import com.eylmz.master.sonar.client.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Override
    public User getUser(String login) {
        return this.userRepository.findUserByLogin(login);
    }

    @Override
    public void addUser(User user) {
        logger.info("user added: {0}", user.getLogin());
        userRepository.save(user);
    }

    @Override
    public List<User> listUsers(String uuid) {
        return userRepository.findUsersByUuid(uuid);
    }

}
