

package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {
    
    @FXML
    private Label lblErrorLogin;
    
    @FXML
    private PasswordField passPassword;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnMaximize;
    
    @FXML
    private Button btnMinimize;
    
    @FXML
    private ImageView loadCycle;
    
    @FXML
    private AnchorPane topPane;
    
    private double xOffset = 0;
    private double yOffset = 0; 
    
    @FXML
    private void initialize() throws IOException {
        // Add mouse listeners for dragging the window
        topPane.setOnMousePressed(this::onMousePressed);
        topPane.setOnMouseDragged(this::onMouseDragged);
        
        loadCycle.setVisible(false);
    }
    
    private void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void onMouseDragged(MouseEvent event) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    
    @FXML
    private void maximizeWindow() {
    Stage stage = (Stage) topPane.getScene().getWindow();
    
        if (stage.isMaximized()) {
            stage.setMaximized(false); // Restore the window
        } else {
            stage.setMaximized(true); // Maximize the window
        }
    }
    
    @FXML
    private void minimizeWindow() {
            
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setIconified(true); // Minimize the stage
    }
    
    
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary1");
    }  
    
    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("signupUI");
    }  
    
    @FXML
    private void btnLoginAction() throws IOException{                                         
        
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        
        // Set the action to be performed after the delay
        delay.setOnFinished(event -> {
            try {
                switchToPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        String userName = txtUsername.getText();
        String passWord = passPassword.getText();
                //String.valueOf(passPassword);
        
        String sqlSelect = "SELECT * FROM USERS WHERE BINARY user_name=? AND BINARY password=?";
        
        try{
           
            Connection myConn = ConDB.getConnection();
            PreparedStatement ps = myConn.prepareStatement(sqlSelect);
            
            ps.setString(1, userName);
            ps.setString(2, passWord);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                loadCycle.setVisible(true);
                // Start the delay
                delay.play();
                
            } else{
                lblErrorLogin.setText("Error"); //New error
            }
            
        }catch(Exception e){
            System.out.println(e);
            
        }
    }

}
