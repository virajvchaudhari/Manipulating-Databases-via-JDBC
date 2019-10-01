package com.jdbcproj.practice.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatesManager {

    public static void displayAllRows(ResultSet rs) throws SQLException {
        String sql = "Select adminId, userName, password " +
                "FROM admin";

        while(rs.next()){
            StringBuffer buffer = new StringBuffer();
            buffer.append(rs.getString("stateId") + " " +
                    rs.getString("stateName"));
            System.out.println(buffer);
        }
    }
}
