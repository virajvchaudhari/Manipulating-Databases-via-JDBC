package com.jdbcproj.practice.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
    public static void displayData(ResultSet rs, int nrows) throws SQLException {
//        rs.last();States
//        int nrows = rs.getRow();
        if (nrows == 0)
            System.out.println("No Tours were found.");
        else {
            System.out.println("No of Tours: " + nrows);
            rs.beforeFirst();

            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();

                //        Column names are not case sensitive.
                int tourId = rs.getInt("tourId");
                String tourName = rs.getString("tourName");
                double price = rs.getDouble("price");
//
//                int tourId = rs.getObject("tourId",Integer.class);
//                String tourName = rs.getObject("tourName", String.class);
//                BigDecimal price = rs.getObject("price", BigDecimal.class);

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
