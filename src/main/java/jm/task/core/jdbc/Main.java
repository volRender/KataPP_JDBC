package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

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

        //drop table Users
    //    daoJDBC.dropUsersTable();
    }
}
