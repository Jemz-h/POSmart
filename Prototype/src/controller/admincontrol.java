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

    @FXML 
    private TextField user, pass;

    @FXML 
    private Button add;

    @FXML 
    private Label addlbl;

    @FXML
    private ImageView obito;

    public void add() throws SQLException {
        String username = user.getText();
        String password = pass.getText();

        System.out.println("I AM WORKING 1");
        // Database connection details
        String dbUrl = "jdbc:mysql://localhost:3306/testing_db";
        String dbUser = "jemz";
        String dbPassword = "123";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            System.out.println("I AM WORKING");
            
            // Correct SQL syntax with parameter placeholders
            String query = "INSERT INTO users (name, pass) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            // Use executeUpdate for INSERT statements. It returns the number of rows inserted.
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
                addlbl.setText("User added successfully!");
                // Clear the textfields after a successful addition
                user.clear();
                pass.clear();
            } else {
                System.out.println("User was not added.");
                addlbl.setText("Error: User was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            addlbl.setText("SQL error occurred.");
        }
    }
}