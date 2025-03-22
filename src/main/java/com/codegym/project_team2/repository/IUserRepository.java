package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.User;

public interface IUserRepository {
    User getUserByUserName(String username);
    boolean registerUser(User user);
}
