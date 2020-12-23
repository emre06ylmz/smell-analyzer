package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.User;

import java.util.List;

public interface IUserService {

    User getUser(String login);
    void addUser(User user);
    List<User> listUsers(String uuid);
}
