package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
             String query = "CREATE TABLE users (id BIGINT AUTO_INCREMENT, " +
                        "name VARCHAR(15), lastname VARCHAR(25), age TINYINT, PRIMARY KEY (id));";
             connection.setAutoCommit(false);
             statement.executeUpdate(query);
             connection.commit();
        } catch (SQLException e) {
            getConnection().rollback();
            System.out.println("Эта таблица уже существует или произошла иная ошибка операции");
        }
    }

    public void dropUsersTable() throws SQLException {;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            String query = "DROP TABLE users";
            connection.setAutoCommit(false);
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            getConnection().rollback();
            System.out.println("Этой таблицы не существует или произошла иная ошибка операции");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String query = "INSERT INTO users (name, lastname, age) values(?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            getConnection().rollback();
            System.out.println("Ошибка при создании пользователя");
        }
    }

    public void removeUserById(long id) throws SQLException {
        String query = "DELETE FROM users where ID=?";
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            getConnection().rollback();
            System.out.println("Ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT id, name, lastname, age FROM users";
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery(query);
            connection.commit();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            getConnection().rollback();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = "TRUNCATE TABLE users";

            connection.setAutoCommit(false);
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            getConnection().rollback();
            System.out.println("Ошибка при очистке таблицы");
        }
    }
}
