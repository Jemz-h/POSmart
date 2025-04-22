package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class logincon {

    // @FXML connects your UI in JavaFX to the controllers
    @FXML
    private TextField user;  
    
    @FXML
    private PasswordField pass;
    
    @FXML 
    private ImageView obito;
    
    @FXML
    private Label loginlbl;
    
    public void loginbtn() throws SQLException, IOException {
        
        // Create an object for the username & password input
        String username = user.getText();
        String password = pass.getText();
        
        // Database connection details
        String dbUrl = "jdbc:mysql://localhost:3306/testing_db"; // Database source
        String dbUser = "jemz";                                  // Database username
        String dbPassword = "123";                               // Database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) { // Create an object for the MySQL connector with the needed credentials
            String query = "SELECT * FROM users WHERE name = ? AND pass = ?"; // This is the query that will be pushed in the MySQL with the '?' being the placeholders
            PreparedStatement statement = connection.prepareStatement(query); // This stores the query with the connector and is ready to be pushed in MySQL once allowed
            statement.setString(1, username); // Sets the value for the first placeholder using user input
            statement.setString(2, password); // Sets the value for the second placeholder using user input
            ResultSet resultSet = statement.executeQuery(); // Acts as the container for the data when the query is executed

            if (resultSet.next()) { // The account exists

                // Retrieve the user name from the result set
                String accountName = resultSet.getString("name");

                if ("admin".equals(accountName)) { // Checks if the user is the admin
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminlogin.fxml")); // Connects to the adminlogin.fxml allowing you to add account
                    Stage stage = (Stage) obito.getScene().getWindow(); // Sets the stage for the application
                    Scene scene = new Scene(root); // Creates a scene for the contents
                    stage.setScene(scene); // Sets the first scene
                    stage.show(); // Shows the program
                    
                } else {

                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/test.fxml")); // Connects to the normal login page
                    Stage stage = (Stage) obito.getScene().getWindow(); // Sets the stage for the application
                    Scene scene = new Scene(root); // Creates a scene for the contents
                    stage.setScene(scene); // Sets the first scene
                    stage.show(); // Shows the program
                }
                
            } else { // The account doesn't exists
                loginlbl.setText("Account not found."); // Skill issue
            }
        }
    }
}
   