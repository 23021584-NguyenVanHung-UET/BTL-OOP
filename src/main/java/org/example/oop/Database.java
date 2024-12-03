package org.example.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection connectDB() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/data_library";
            String user = "root";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công.");

        } catch (SQLException e) {
            System.out.println("Kết nối thất bại.");
            e.printStackTrace();
        }
        return connection;
    }
}