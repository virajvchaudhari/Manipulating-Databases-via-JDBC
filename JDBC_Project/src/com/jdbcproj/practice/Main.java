package com.jdbcproj.practice;

import com.jdbcproj.practice.beans.Admin;
import com.jdbcproj.practice.tables.AdminManager;
import com.jdbcproj.practice.tables.Tours;
import com.jdbcproj.practice.util.ConnectionManager;
import com.jdbcproj.practice.util.InputHelper;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    /**
     *  Getting the list of tables column names and its type.
     */

    public static void main(String[] args) throws Exception{
        Connection conn = ConnectionManager.getInstance().getConnection();
        ResultSet rsTables = null;
        ResultSet rsColumns = null;
        ArrayList<String> tables = new ArrayList<>();

        try{
            DatabaseMetaData metaData = conn.getMetaData();
            String[] tableTypes = {"TABLE"};
            rsTables = metaData.getTables(null,
                    "%","%",tableTypes);

            while (rsTables.next()){
                tables.add(rsTables.getString("TABLE_NAME"));
            }

            for (String tableName: tables) {
                System.out.println("Table: " + tableName);
                System.out.println("--------------------");

                rsColumns = metaData.getColumns(null,
                        "%","tableName","%");

                while (rsColumns.next()){
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(rsColumns.getString("COLUMN_NAME"));
                    buffer.append(": ");
                    buffer.append(rsColumns.getString("TYPE_NAME"));
                    System.out.println(buffer.toString());
                }
                System.out.println();
            }
        }
        catch (Exception e){
            System.err.println(e);
        }
        finally {
            rsTables.close();
            rsColumns.close();
        }
        ConnectionManager.getInstance().close();
    }



    /**
     *  Getting the list of tables as metadata.
     */

   /* public static void main(String[] args) throws Exception{
        Connection conn = ConnectionManager.getInstance().getConnection();
        ResultSet rsTables = null;

        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String[] tableTypes = {"TABLE"};
            rsTables = metaData.getTables(null,"%",
                    "%",tableTypes);

            while (rsTables.next()){
                System.out.println(rsTables.getString("TABLE_NAME"));
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        finally {
            if (rsTables != null)
                rsTables.close();
        }
        ConnectionManager.getInstance().close();
    */






    /**
     *  Deleting a record from the admin table.
     */

    /*public static void main(String[] args) throws SQLException{
        AdminManager.displayAllRows();

        int adminId = InputHelper.getIntegerInput("Enter the row to delete: ");

        if(AdminManager.delete(adminId)){
            System.out.println("Deleted!");
        }else {
            System.err.println("Deletion Unsuccessful!");
        }
    }

*/

    /**
     * Updating a record from the admin table (old and new methods both).
     */


   /* public static void main(String[] args) throws Exception {
//      Setting up database type.
        ConnectionManager.getInstance().setDbType(DBType.MYSQL);

//        For transaction purposes - set autocommit = false.
        Connection conn = ConnectionManager.getInstance().getConnection();
        conn.setAutoCommit(false);

        AdminManager.displayAllRows();

        int adminId = InputHelper.getIntegerInput("Enter the row to update: ");
        Admin bean = AdminManager.getRow(adminId);

        if (bean == null) {
            System.err.println("No rows were updated.");
            return;
        }

        String password = InputHelper.getInput("Enter a password");
        bean.setPassword(password);

        if (AdminManager.updateNew(bean)) {
            System.out.println("Success!");
        } else {
            System.out.println("Unsuccessful Update!");
        }

        conn.commit();
        System.out.println("Transaction Committed.");
//      Closing the connection.
        ConnectionManager.getInstance().close();
    }
*/
    /**
     *  Inserting rows into the admin table using SQL.
     */
    /*public static void main(String[] args) throws SQLException {
        AdminManager.displayAllRows();

        Admin bean = new Admin();

        bean.setUserName(InputHelper.getInput("Enter UserName: "));
        bean.setPassword(InputHelper.getInput("Enter Password: "));

        boolean result = AdminManager.insert(bean);

        if (result) {
            System.out.println("New row with primary key "
            + bean.getAdminId() + " was inserted successfully !");
        }

    }*/


    /**
     * Accessing the admin table and retrieving a particular row data using setters and getters.
     */

//    public static void main(String[] args) throws SQLException {
//        AdminManager.displayAllRows();
//
//        int adminId = InputHelper.getIntegerInput("Select a row: ");
//        Admin bean = AdminManager.getRow(adminId);
//
//        if (bean == null) {
//            System.err.println("No rows were found.");
//        }
//        else {
//            System.out.println("Admin ID: " + bean.getAdminId());
//            System.out.println("Admin UserName: " + bean.getUserName());
//            System.out.println("Admin Password: " + bean.getPassword());
//        }
//    }


/**
 * For accessing tables such as tours, states and retrieving particular table data.
 */
/*
        private static final String SQL = "{call gettourswithcountbyprice(?, ?)}";

        public static void main(String[] args) throws SQLException {
        double maxPrice;
        try {
            maxPrice = InputHelper.getDoubleInput("Enter Maximum Price: ");
        } catch (NumberFormatException e) {
            System.err.println("Error invalid number.");
            return;
        }


        ResultSet rs = null;

        try (Connection conn = DBUtil.getConnection(DBType.MYSQL);
             CallableStatement stmt = conn.prepareCall(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
        ) {
            stmt.setDouble(1,maxPrice);
            stmt.registerOutParameter("total", Types.INTEGER);
            rs = stmt.executeQuery();

            int nrows = stmt.getInt("total");
//            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Tours.displayData(rs,nrows);
        } catch (SQLException e) {
            DBUtil.processException(e);
        }
    }*/
}
