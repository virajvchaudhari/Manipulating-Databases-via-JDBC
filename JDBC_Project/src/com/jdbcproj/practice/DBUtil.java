package com.jdbcproj.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "magic123";
    private static final String M_CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";
    private static final String H_CONN_STRING = "jdbc:hsqldb:data/explorecalifornia";

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch (dbType){
            case MYSQL:
                return DriverManager.getConnection(M_CONN_STRING,USERNAME,PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(H_CONN_STRING,USERNAME,PASSWORD);
            default:
                return null;
        }
    }

    public static void processException(SQLException e){
        System.err.println("Error Message: " + e.getMessage());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("SQL State: " + e.getSQLState());
    }

}
