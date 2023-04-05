package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl service = new UserServiceImpl();

        //create table Users
        service.createUsersTable();

        //create 4 users for table data
        service.saveUser("Daniil", "Litvishko", (byte) 23);
        service.saveUser("Vladislav", "Varakin", (byte) 23);
        service.saveUser("Nikita", "Vasiliev", (byte) 22);
        service.saveUser("Lena", "Soroka", (byte) 22);

        //get all users info from the table
        List<User> userList = service.getAllUsers();
        for (User u :userList) {
            System.out.println(u);
        }

        //clean all data from the table
        service.cleanUsersTable();

        //drop table Users
        service.dropUsersTable();
    }
}
