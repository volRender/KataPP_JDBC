package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = "CREATE TABLE users (id BIGINT AUTO_INCREMENT, " +
                    "name VARCHAR(15), lastname VARCHAR(25), age TINYINT, PRIMARY KEY (id));";
            connection.setAutoCommit(false);
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            System.out.println("Эта таблица уже существует или произошла иная ошибка операции");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = "DROP TABLE users";
            connection.setAutoCommit(false);
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            System.out.println("Этой таблицы не существует или произошла иная ошибка операции");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO users (name, lastname, age) values(?, ?, ?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            System.out.println("Ошибка при создании пользователя");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM users where ID=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);

            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            System.out.println("Ошибка при удалении пользователя");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT id, name, lastname, age FROM users";
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();

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
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            System.out.println("Ошибка при выводе данных таблицы");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = "TRUNCATE TABLE users";

            connection.setAutoCommit(false);
            statement.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            System.out.println("Ошибка при очистке таблицы");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
