package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE users (id BIGINT AUTO_INCREMENT, " +
                    "name VARCHAR(15), lastname VARCHAR(25), age TINYINT, PRIMARY KEY (id));";
            statement.executeUpdate(query);
            System.out.println("Table users was created!");


        } catch (SQLException e) {
            System.out.println("This table already exists or incorrect sql query");
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "DROP TABLE users";
            statement.executeUpdate(query);
            System.out.println("Table users was dropped!");


        } catch (SQLException e) {
            System.out.println("This table is not exists or incorrect sql query");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
