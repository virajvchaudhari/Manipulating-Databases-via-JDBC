package com.jdbcproj.practice.tables;

import com.jdbcproj.practice.DBType;
import com.jdbcproj.practice.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

public class ToursManager {
    public static void displayAllRows() throws SQLException {

        String sql = "Select adminId, userName, password " +
                "FROM admin";

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();

                //        Column names are not case sensitive.
                int tourId = rs.getInt("tourId");
                String tourName = rs.getString("tourName");
                double price = rs.getDouble("price");

                buffer.append("Tour: " + tourId);
                buffer.append(tourName + " Price: ");

                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String formattedPrice = nf.format(price);
                buffer.append(formattedPrice);

                System.out.println(buffer.toString());
            }
        }
    }
}

