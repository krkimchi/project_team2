package com.codegym.project_team2.service;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.repository.IUserRepository;

public class UserService implements IUserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username) {
        return userRepository.getUserByUserName(username);
    }

    @Override
    public boolean register(User user) {
        return userRepository.registerUser(user);
    }
}
