package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagementRepository implements IUserManagementRepository {

    @Override
    public List<User> getAllUsers() throws SQLException {
        String query = "select * from users";
        List<User> userList = new ArrayList<>();

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAvatarUrl(resultSet.getString("avatar_url"));
                user.setUserType(User.UserType.valueOf(resultSet.getString("user_type")));
                user.setActive(resultSet.getBoolean("is_active"));
                user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        String query = "update users set username = ?, email = ?, phone = ?, full_name = ?, avatar_url = ?, user_type = ?, is_active = ? where id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setString(5, user.getAvatarUrl());
            preparedStatement.setString(6, user.getUserType().name());
            preparedStatement.setBoolean(7, user.isActive());
            preparedStatement.setInt(8, user.getId());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    @Override
    public boolean deleteUser(int userId) throws SQLException {
        String query = "delete from users where id = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    @Override
    public List<User> searchUsers(String query) throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "select * from users where username like ? or full_name like ? or email like ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            String searchPattern = "%" + query + "%";
            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, searchPattern);
            preparedStatement.setString(3, searchPattern);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAvatarUrl(resultSet.getString("avatar_url"));
                user.setUserType(User.UserType.valueOf(resultSet.getString("user_type")));
                user.setActive(resultSet.getBoolean("is_active"));
                user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                userList.add(user);
            }
        }

        return userList;
    }
}
