package com.example.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigDB {


    public static Connection provideConnection() throws ClassNotFoundException {
        Connection conn = null;
        final Properties props =  new Properties();

        Class.forName("com.mysql.jdbc.Driver");
        String theUser = props.getProperty("user");
        String thePassword = props.getProperty("password");
        String theDBUrl = props.getProperty("dburl");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mana_book?autoReconnect=true&useSSL=false", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
