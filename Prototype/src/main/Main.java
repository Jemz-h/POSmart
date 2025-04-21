package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application{
    
    // Create static variable for Scene & Stage
    private Stage window;
    private Scene home;
    
    // Executes the program
    public static void main(String[] args) {
     launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        // Create an object for the FXML
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        
        home = new Scene(root, 1280, 720); // Sets the default size of the application
        window.setTitle("Home"); // Sets the application name to "Home"
        window.setScene(home);
        window.show(); // Shows the Application
        
    }
}
