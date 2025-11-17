package com.todoapp;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();

    private static String getEnv(String key, String def) {
        String v = DOTENV.get(key);
        if (v == null || v.isBlank()) v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v;
    }

    private static final String HOST = getEnv("POSTGRES_HOST", "localhost");
    private static final String PORT = getEnv("POSTGRES_HOST_PORT", "5432");
    private static final String DB   = getEnv("POSTGRES_DB", getEnv("POSTGRES_DATABASE", "java_crud_db"));
    private static final String USER = getEnv("POSTGRES_USER", "root");
    private static final String PASS = getEnv("POSTGRES_PASSWORD", "root");

    private static final String JDBC_URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB;

    private static final String INSERT_USERS_QUERY = "INSERT INTO users (name, email) VALUES (?, ?)";
    private static final String SELECT_USERS_BY_ID_QUERY = "SELECT id, name, email FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT id, name, email FROM users ORDER BY id";
    private static final String DELETE_USERS_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ?, email = ? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
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
