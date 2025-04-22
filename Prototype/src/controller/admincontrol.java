package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class admincontrol {

    // @FXML connects your UI in JavaFX to the controllers
    @FXML 
    private TextField user, pass;

    @FXML 
    private Button add;

    @FXML 
    private Label addlbl;

    @FXML
    private ImageView obito;

    public void add() throws SQLException {
        
        // Create an object for the username & password input
        String username = user.getText();
        String password = pass.getText();

        // Database connection details
        String dbUrl = "jdbc:mysql://localhost:3306/testing_db"; // Database source
        String dbUser = "jemz";                                  // Database username
        String dbPassword = "123";                               // Database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) { // Create an object for the MySQL connector with the needed credentials
            
            // Correct SQL syntax with parameter placeholders
            String query = "INSERT INTO users (name, pass) VALUES (?, ?)"; // This is the query that will be pushed in the MySQL with the '?' being the placeholders
            PreparedStatement statement = connection.prepareStatement(query); // This stores the query with the connector and is ready to be pushed in MySQL once allowed
            statement.setString(1, username); // Sets the value for the first placeholder using user input
            statement.setString(2, password); // Sets the value for the second placeholder using user input
            
            // Use executeUpdate for INSERT statements. It returns the number of rows inserted.
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) { // Checks if there are rows inserted in MySQL
                
                addlbl.setText("User added successfully!"); // Confirms if the user is added successfully
                
                // Clear the textfields after a successful addition
                user.clear();
                pass.clear();
                
            } else {
                addlbl.setText("Error: User was not added."); // Skill issue
            }
        } catch (SQLException e) { // MySQL connection failed
            e.printStackTrace();
            addlbl.setText("SQL error occurred."); 
        }
    }
}