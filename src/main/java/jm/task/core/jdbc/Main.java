package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

        //create table Users
        daoJDBC.createUsersTable();

        //create 4 users for table data
        daoJDBC.saveUser("Daniil", "Litvishko", (byte) 23);
        daoJDBC.saveUser("Vladislav", "Varakin", (byte) 23);
        daoJDBC.saveUser("Nikita", "Vasiliev", (byte) 22);
        daoJDBC.saveUser("Lena", "Soroka", (byte) 22);

        //get all users info from database
        List<User> userList = daoJDBC.getAllUsers();
        for (User u :userList) {
            System.out.println(u);
        }

        //drop table Users
        daoJDBC.dropUsersTable();
    }
}
