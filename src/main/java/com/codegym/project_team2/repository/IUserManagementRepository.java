package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.User;
import java.sql.SQLException;
import java.util.List;

public interface IUserManagementRepository {
    List<User> getAllUsers() throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(int userId) throws SQLException;
    List<User> searchUsers(String query) throws SQLException;
}
