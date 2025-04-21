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

    @FXML
    private TextField user;  
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private Button login;
    
    @FXML 
    private ImageView obito;
    
    @FXML
    private Label loginlbl;
    
    public void loginbtn() throws SQLException, IOException {
        String username = user.getText();
        String password = pass.getText();
        
        // Database connection details
        String dbUrl = "jdbc:mysql://localhost:3306/testing_db";
        String dbUser = "jemz";
        String dbPassword = "123";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM users WHERE name = ? AND pass = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                // Retrieve the user name from the result set
                String accountName = resultSet.getString("name");

                if ("admin".equals(accountName)) {
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminlogin.fxml"));
                    Stage stage = (Stage) obito.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Non-admin branch reached. Account name: " + accountName);
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/test.fxml"));
                    Stage stage = (Stage) obito.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show(); 
                }
                
            } else {
                loginlbl.setText("Account not found.");
            }
        }
    }
}
   