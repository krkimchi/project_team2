package com.codegym.project_team2.repository;

import com.codegym.project_team2.model.User;
import com.codegym.project_team2.util.BaseRepository;

import java.sql.*;
import java.time.LocalDateTime;

public class UserRepository implements IUserRepository {

    @Override
    public User getUserByUserName(String username) {
        String query = "select * from users where username = ?";
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
        String query = "insert into users (username, password, email, phone, full_name, user_type, is_active, created_at) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getFullName());
            preparedStatement.setString(6, user.getUserType().name());
            preparedStatement.setBoolean(7, user.isActive());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User mapToUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String fullName = resultSet.getString("full_name");
        String avatarUrl = resultSet.getString("avatar_url");
        String userTypeStr = resultSet.getString("user_type");
        User.UserType userType = User.UserType.valueOf(userTypeStr);
        boolean isActive = resultSet.getBoolean("is_active");
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();

        return new User(id, username, password, email, phone, fullName, avatarUrl, userType, isActive, createdAt);
    }
}
