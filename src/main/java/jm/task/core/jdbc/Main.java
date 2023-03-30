package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        //create table users
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
        daoJDBC.createUsersTable();
    }
}
