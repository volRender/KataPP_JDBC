package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();

        //create table users
        daoJDBC.createUsersTable();

        //drop table users
        daoJDBC.dropUsersTable();
    }
}
