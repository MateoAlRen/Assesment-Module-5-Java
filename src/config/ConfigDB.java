package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class to manage database connection
public class ConfigDB {

    // Static connection object to reuse if needed
    public static Connection objConnection = null;

    // Method to open a database connection
    public static Connection openConnection() {

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database URL, username, and password
            String url = "jdbc:mysql://localhost:3306/LibraryNova";
            String user = "root";
            String password = "Qwe.123*";

            // Establish connection
            objConnection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException error) {
            // Driver class not found
            System.out.println("Driver not found: " + error.getMessage());

        } catch (SQLException error) {
            // Error connecting to database
            System.out.println("Database connection error: " + error.getMessage());
        }

        return objConnection; // Return the connection object
    }
}