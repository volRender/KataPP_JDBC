package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE users (id BIGINT AUTO_INCREMENT, " +
                    "name VARCHAR(15), lastname VARCHAR(25), age TINYINT, PRIMARY KEY (id));";
            statement.executeUpdate(query);
            System.out.println("Таблица Users была создана!");
        } catch (SQLException e) {
            System.out.println("Эта таблица уже существует или ваш sql-запрос некорректен");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String query = "DROP TABLE users";
            statement.executeUpdate(query);
            System.out.println("Таблица Users была удалена!");
        } catch (SQLException e) {
            System.out.println("Этой таблицы не существует или ваш sql-запрос некорректен");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastname, age) values(?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка при создании пользователя");
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
