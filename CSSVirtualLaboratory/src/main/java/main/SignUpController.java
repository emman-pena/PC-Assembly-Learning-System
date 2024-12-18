

package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SignUpController {
    
    @FXML
    private Label lblNote;
    
    @FXML
    private Label lblErrorNote;
     
    @FXML
    private PasswordField passPassWord;
    
    @FXML
    private PasswordField passConPassWord;
    
    @FXML
    private TextField txtUserName;
    
    @FXML
    private TextField txtFirstName;
    
    @FXML
    private TextField txtMiddleName;
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnMaximize;
    
    @FXML
    private Button btnMinimize;
    
    @FXML
    private AnchorPane topPane;
    
    private double xOffset = 0;
    private double yOffset = 0; 
    
    @FXML
    private void initialize() throws IOException {
        // Add mouse listeners for dragging the window
        topPane.setOnMousePressed(this::onMousePressed);
        topPane.setOnMouseDragged(this::onMouseDragged);
        
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
    private void switchToLogin() throws IOException {
        App.setRoot("loginUI");
    }  
    
    @FXML
    private void btnSignUpAction() throws IOException {                                          
        
        String fname = txtFirstName.getText();
        String mname = txtMiddleName.getText();
        String lname = txtLastName.getText();
        String user = txtUserName.getText();
        String pass = passPassWord.getText();
        
        
        if(pass == null ? passConPassWord.getText() == null : pass.equals(passConPassWord.getText())){
            
            try{
              
               Connection myConn = ConDB.getConnection();
               Statement stat = myConn.createStatement();
                
               String sqlInsert = "INSERT INTO USERS(first_name, middle_initial, last_name, user_name, password)" + 
                       "VALUES(?,?,?,?,?)";
               String sqlCheck = "SELECT * FROM USERS WHERE user_name='" + user + "'";
               
               ResultSet rs = stat.executeQuery(sqlCheck);
               
               if(rs.next()){

                   lblErrorNote.setText("Username is already taken.");
                   txtFirstName.setText("");
                   txtMiddleName.setText("");
                   txtLastName.setText("");
                   txtUserName.setText("");
                   passPassWord.setText("");
                   passConPassWord.setText("");
                   
               } else{
                   
                   PreparedStatement stmt = myConn.prepareStatement(sqlInsert);
                   
                   stmt.setString(1, fname);
                   stmt.setString(2, mname);
                   stmt.setString(3, lname);
                   stmt.setString(4, user);
                   stmt.setString(5, pass);
                   
                   int rowsInserted = stmt.executeUpdate();
                   
                   if(rowsInserted > 0){
                       
                       lblNote.setText("Added successfully");
                       txtFirstName.setText("");
                       txtMiddleName.setText("");
                       txtLastName.setText("");
                       txtUserName.setText("");
                       passPassWord.setText("");
                       passConPassWord.setText("");
                       
                   }
               }
               
            }catch(Exception e){
                  lblErrorNote.setText(e.toString()); //this is the error
                  System.out.println(e);
            }
            
        }else {
            
        }
        
    }                       
    
    
}
