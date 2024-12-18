

package main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
        
import javafx.scene.Group;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class PCAssemblyController implements Initializable{

    /*
    
    DONE - Verify that the "socket.PNG" file is located in the same directory as the App class file. If it is not, move the file to the correct directory.

    DONE - Check that the file name and extension are correct. Ensure that the file name is spelled correctly, including capitalization, and that the file extension is ".PNG" (not ".png" or ".jpeg" or any other extension).

    Make sure that the file is included in the project's build path. If the file is not included in the build path, it will not be included in the compiled program and will not be accessible at runtime.

    Not working - Try using a relative file path instead of the getResource() method. For example, if the "socket.PNG" file is located in the same directory as the App class file, you can use the following code:

    Image monitorImg = new Image("file:socket.PNG");

     Not Viable - If the issue persists, try using an absolute file path to the "socket.PNG" file. For example:

    Image monitorImg = new Image("file:C:/path/to/socket.PNG");

    Replace "C:/path/to/" with the actual path to the file on your system.
    */
    
    //Relative file paths
    Image monitorImg = new Image("/images/monitor.PNG");
    Image systemUnitImg = new Image("images/systemUnit.png");
    Image mouseImg = new Image("images/mouse.png");
    Image keyboardImg = new Image("/images/keyboard.png");
   
    
    /*
    Absolute file paths
    Image systemUnitImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\TEMPLATE.png");
    Image monitorImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\socket.png");
    Image mouseImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\coin_cell.png");
    Image keyboardImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\RAM.png");
    Image hardDiskImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\hard-disk-drive.jpg");
    Image powerSupplyImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\Power Supply.png");
    */
    Group parentContainer = new Group();
   
    
    DraggableMaker draggableMaker = new DraggableMaker();
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }  
   
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnMaximize;
    
    @FXML
    private Button btnMinimize;
    
    //draggable top pane --------------
    
    @FXML
    private AnchorPane topPane;
    
    private double xOffset = 0;
    private double yOffset = 0; 
    
    @FXML
    private Rectangle monitor;
    
    @FXML
    private Rectangle systemUnit;
    
    @FXML
    private Rectangle mouse;
    
     @FXML
    private Rectangle mousePos;
    
    @FXML
    private Rectangle monitorPos;
    
    @FXML
    private Rectangle pcBounds;
    
    @FXML
    private Rectangle keyboardPos;
    
    @FXML
    private Rectangle keyboard;
    
    @FXML
    private Rectangle systemUnitPos;
    
    
    @FXML
    private Button onButton;
    
    @FXML
    private void bringOnButton() {
        onButton.toFront();
    }
    
    @FXML
    private void switchToFinishDialog() throws IOException {
        App.setRoot("finishDialog");
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }

    
    
    AnimationTimer collisionTimer = new AnimationTimer(){
        @Override
        public void handle(long timestamp){
            
           checkCollisionSystemUnit(systemUnit, pcBounds);
           checkCollisionMonitor(monitor, pcBounds);
           checkCollisionKeyBoard(keyboard, pcBounds);
           checkCollisionMouse(mouse, pcBounds);
           
           
           /*
           if(cpuDraggable == true){
               checkCollisionCPU(cpu, cpuPos);
           }
           */
           
           
        }
    };
    
    public void initialize(URL url, ResourceBundle rb) {
        
        // Add mouse listeners for dragging the window
        topPane.setOnMousePressed(this::onMousePressed);
        topPane.setOnMouseDragged(this::onMouseDragged);
        
        draggableMaker.makeDraggable(systemUnit);
        
        systemUnit.setFill(new ImagePattern(systemUnitImg));
        monitor.setFill(new ImagePattern(monitorImg));
        keyboard.setFill(new ImagePattern(keyboardImg));
        mouse.setFill(new ImagePattern(mouseImg));
        
        collisionTimer.start();
        
        
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
    
    public void checkCollisionSystemUnit(Rectangle systemUnit, Rectangle pcBounds){
        
        if(systemUnit.getBoundsInParent().intersects(pcBounds.getBoundsInParent())){
            
        } else{
            
            
            systemUnitPos.setFill(new ImagePattern(systemUnitImg));
            
            parentContainer.getChildren().add(systemUnit);
            parentContainer.getChildren().remove(systemUnit);
            draggableMaker.makeDraggable(monitor);
            
        }
    }
   
    
    public void checkCollisionMonitor(Rectangle monitor, Rectangle pcBounds){
        
        if(monitor.getBoundsInParent().intersects(pcBounds.getBoundsInParent())){
              
            
            
        } else{
            
            monitorPos.setFill(new ImagePattern(monitorImg));
            
            parentContainer.getChildren().add(monitor);
            parentContainer.getChildren().remove(monitor);
            draggableMaker.makeDraggable(keyboard);
            
        }
    }
    
     public void checkCollisionKeyBoard(Rectangle keyboard, Rectangle pcBounds){
        
        if(keyboard.getBoundsInParent().intersects(pcBounds.getBoundsInParent())){
              
            
            
        } else{
            
            keyboardPos.setFill(new ImagePattern(keyboardImg));
            
            parentContainer.getChildren().add(keyboard);
            parentContainer.getChildren().remove(keyboard);
            draggableMaker.makeDraggable(mouse);
            
        }
    }
     
     public void checkCollisionMouse(Rectangle mouse, Rectangle pcBounds){
        
        if(mouse.getBoundsInParent().intersects(pcBounds.getBoundsInParent())){
              
            
            
        } else{
            
            mousePos.setFill(new ImagePattern(mouseImg));
            
            parentContainer.getChildren().add(mouse);
            parentContainer.getChildren().remove(mouse);
            bringOnButton();
            
        }
    }
     
    
    
    
    /* Restarts drag
    public void checkCollisionCPU(Rectangle cpu, Rectangle cpuPos){
        
        if(cpu.getBoundsInParent().intersects(cpuPos.getBoundsInParent())){
            
            } else {
            cpu.setLayoutX(cpuPos.getLayoutX());
            cpu.setLayoutY(cpuPos.getLayoutY());
            cpuPos.setFill(new ImagePattern(systemUnitImg)); // Change the fill of the CPU socket to the motherboard image
            parentContainer2.getChildren().remove(cpu); // Remove the CPU from the group it was added to
            cpuPos.toBack(); // Move the CPU socket to the back, so it's below the CPU
        }
        }
     
    */
    
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
    
}
