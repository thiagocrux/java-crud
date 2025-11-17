package com.todoapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/java_crud_db";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "root";

    private static final String INSERT_USERS_QUERY = "INSERT INTO users (name, email) VALUES (?, ?);";
    private static final String SELECT_USERS_BY_ID_QUERY = "SELECT id, name, email FROM users WHERE id = ?;";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users;";
    private static final String DELETE_USERS_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, email = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException exception) {
            System.err.println("Erro ao conectar: " + exception.getMessage());
            exception.printStackTrace();
        }

        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_QUERY)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println("Erro ao inserir usuário: " + exception.getMessage());
            exception.printStackTrace();
            throw exception;
        }
    }

    public User selectUser(int id) {
        User user = null;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
            }
        } catch (SQLException error) {
            printSQLException(error);
        }

        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email")));
            }
        } catch (SQLException error) {
            printSQLException(error);
        }

        return users;
    }

    public boolean updateUser(User user) throws SQLException {
        int updatedRow;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getId());
            updatedRow = preparedStatement.executeUpdate();
        }

        return updatedRow > 0;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_QUERY)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }

        return rowDeleted;
    }

    private void printSQLException(SQLException exceptions) {
        for (Throwable exception : exceptions) {
            exception.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException) exception).getSQLState());
            System.err.println("Error Code: " + ((SQLException) exception).getErrorCode());
            System.err.println("Message: " + exception.getMessage());
            Throwable throwable = exception.getCause();

            while (throwable != null) {
                System.out.println("Cause: " + throwable.getMessage());
                throwable = throwable.getCause();
            }
        }
    }
}
