package com.jdbcproj.practice.util;

import com.jdbcproj.practice.DBType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;

    private final String USERNAME = "dbuser";
    private final String PASSWORD = "magic123";
    private final String H_CONN_STRING = "jdbc:hsqldb:data/explorecalifornia";
    private final String M_CONN_STRING = "jdbc:mysql://localhost/explorecalifornia";

//    Change the DBType accordingly.
    private DBType dbType = DBType.MYSQL;

    private Connection conn = null;

    private ConnectionManager(){
    }

//  Setting up instance of the connection.
    public static ConnectionManager getInstance(){
        if (instance == null)
            instance = new ConnectionManager();
        return instance;
    }

//  Setting up the Database Type.
    public void setDbType(DBType dbType){
        this.dbType = dbType;
    }

//
    public boolean openConnection(){
        try{
            switch (dbType){
                case MYSQL:
                    conn = DriverManager.getConnection(M_CONN_STRING,USERNAME,PASSWORD);
                    return true;
                case HSQLDB:
                    conn = DriverManager.getConnection(H_CONN_STRING,USERNAME,PASSWORD);
                    return true;
                default:
                    return false;
            }
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }

    public Connection getConnection(){
        if (conn == null){
            if (openConnection()){
                System.out.println("Connection Opened...");
                return conn;
            }
            else
                return null;
        }
        return conn;
    }

    public void close(){
        System.out.println("Closing Connection...");
        try{
            conn.close();
            conn = null;
        }catch (Exception e){
        }
    }
}
