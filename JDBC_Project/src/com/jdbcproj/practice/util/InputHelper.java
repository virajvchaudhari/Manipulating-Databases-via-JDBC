package com.jdbcproj.practice.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {
    public static String getInput(String prompt){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(prompt);
        System.out.flush();

        try{
            return stdin.readLine();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    public static double getDoubleInput(String prompt){
        String input = getInput(prompt);
        return Double.parseDouble(input);
    }

    public static int getIntegerInput(String prompt){
        String input = getInput(prompt);
        return Integer.parseInt(input);
    }
}
