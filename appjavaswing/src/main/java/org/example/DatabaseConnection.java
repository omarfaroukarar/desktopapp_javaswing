package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://autorack.proxy.rlwy.net:42235/railway";
    private static final String DB_USER = "root"; // Change this to your MySQL username
    private static final String DB_PASSWORD = "vhPkNKjabYHhsSMzIgCInpEpRWSySNLh"; // Change this to your MySQL password

    // Method to establish a connection to the database
    public Connection connect() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }

    // Method to insert a new user into the database
    public void insertUser(String username, String password) {
        String insertSQL = "INSERT INTO useuse (username, password) VALUES (?, ?)";

        // Using try-with-resources to ensure resources are closed automatically
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            // Check if the connection is valid
            if (connection == null) {
                System.out.println("Connection is null. Cannot insert user.");
                return;
            }

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }
}
