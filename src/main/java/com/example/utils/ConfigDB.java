package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection provideConnection() throws ClassNotFoundException {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mana_book?autoReconnect=true&useSSL=false", "root", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
