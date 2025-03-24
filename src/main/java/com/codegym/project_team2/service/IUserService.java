package com.codegym.project_team2.service;

import com.codegym.project_team2.model.User;

public interface IUserService {
    User login(String username);
    boolean register(User user);
}
