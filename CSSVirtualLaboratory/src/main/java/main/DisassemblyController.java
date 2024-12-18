

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


public class DisassemblyController implements Initializable{

    /*
    
    DONE - Verify that the "socket.PNG" file is located in the same directory as the App class file. If it is not, move the file to the correct directory.

    DONE - Check that the file name and extension are correct. Ensure that the file name is spelled correctly, including capitalization, and that the file extension is ".PNG" (not ".png" or ".jpeg" or any other extension).

    Make sure that the file is included in the project's build path. If the file is not included in the build path, it will not be included in the compiled program and will not be accessible at runtime.

    Not working - Try using a relative file path instead of the getResource() method. For example, if the "socket.PNG" file is located in the same directory as the App class file, you can use the following code:

    Image cpuImg = new Image("file:socket.PNG");

     Not Viable - If the issue persists, try using an absolute file path to the "socket.PNG" file. For example:

    Image cpuImg = new Image("file:C:/path/to/socket.PNG");

    Replace "C:/path/to/" with the actual path to the file on your system.
    */
    
    //Relative file paths
    Image cpuImg = new Image("/images/socket.PNG");
    Image boardImg = new Image("images/TEMPLATE.png");
    Image coinCellImg = new Image("images/coin_cell.png");
    Image ramImg = new Image("/images/RAM.png");
    Image hardDiskImg = new Image("/images/hard-disk-drive.jpg");
    Image powerSupplyImg = new Image("/images/Power Supply.png");
    
    /*
    Absolute file paths
    Image boardImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\TEMPLATE.png");
    Image cpuImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\socket.png");
    Image coinCellImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\coin_cell.png");
    Image ramImg = new Image("file:C:\\Users\\ethan\\OneDrive\\Desktop\\System\\Virtual Laboratory CSS - v1.8\\CSSVirtualLaboratory\\src\\main\\resources\\images\\RAM.png");
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
    private Rectangle motherboard;
    
    @FXML
    private Rectangle cpu;
    
    @FXML
    private Rectangle coinCell;
    
     @FXML
    private Rectangle coinCellPos;
    
    @FXML
    private Rectangle cpuPos;
    
    @FXML
    private Rectangle cpuBounds;
    
    @FXML
    private Rectangle pcCase;
    
    @FXML
    private Rectangle outerPartsBounds;
    
    @FXML
    private Rectangle ramPos;
    
    @FXML
    private Rectangle ram;
    
    @FXML
    private Rectangle powerSupply;
    
    @FXML
    private Rectangle powerSupplyPos;
    
    @FXML
    private Rectangle hardDisk;
    
    @FXML
    private Rectangle hardDiskPos;
    
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
            
           checkCollisionMotherboard(motherboard, pcCase);
           checkCollisionCPU(cpu, cpuBounds);
           checkCollisionRAM(ram, cpuBounds);
           checkCollisionCoinCell(coinCell, cpuBounds);
           checkCollisionHardDisk(hardDisk, outerPartsBounds);
           checkCollisionPowerSupply(powerSupply, outerPartsBounds);
           
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
        
        draggableMaker.makeDraggable(powerSupply);
        
        motherboard.setFill(new ImagePattern(boardImg));
        cpu.setFill(new ImagePattern(cpuImg));
        ram.setFill(new ImagePattern(ramImg));
        coinCell.setFill(new ImagePattern(coinCellImg));
        hardDisk.setFill(new ImagePattern(hardDiskImg));
        powerSupply.setFill(new ImagePattern(powerSupplyImg));
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
    
    public void checkCollisionPowerSupply(Rectangle powerSupply, Rectangle outerPartsBounds) {
        
        if(powerSupply.getBoundsInParent().intersects(outerPartsBounds.getBoundsInParent())){
              
            
            
        } else{
            
            powerSupplyPos.setFill(new ImagePattern(powerSupplyImg));
            
            parentContainer.getChildren().add(powerSupply);
            parentContainer.getChildren().remove(powerSupply);
            draggableMaker.makeDraggable(hardDisk);
            
        }
    }    
    
    public void checkCollisionHardDisk(Rectangle hardDisk, Rectangle outerPartsBounds){
        
        if(hardDisk.getBoundsInParent().intersects(outerPartsBounds.getBoundsInParent())){
              
            
            
        } else{
            
            hardDiskPos.setFill(new ImagePattern(hardDiskImg));
            
            parentContainer.getChildren().add(hardDisk);
            parentContainer.getChildren().remove(hardDisk);
            draggableMaker.makeDraggable(coinCell);
            
        }
    }
    
    public void checkCollisionCoinCell(Rectangle coinCell, Rectangle cpuBounds){
        
        if(coinCell.getBoundsInParent().intersects(cpuBounds.getBoundsInParent())){
              
            
            
        } else{
            
            coinCellPos.setFill(new ImagePattern(coinCellImg));
            
            parentContainer.getChildren().add(coinCell);
            parentContainer.getChildren().remove(coinCell);
            draggableMaker.makeDraggable(ram);
            
        }
    }
    
    public void checkCollisionRAM(Rectangle ram, Rectangle cpuBounds){
        
        if(ram.getBoundsInParent().intersects(cpuBounds.getBoundsInParent())){
              
            
            
        } else{
            
            ramPos.setFill(new ImagePattern(ramImg));
            
            parentContainer.getChildren().add(ram);
            parentContainer.getChildren().remove(ram);
            draggableMaker.makeDraggable(cpu);
            
        }
    }
     
    public void checkCollisionCPU(Rectangle cpu, Rectangle cpuBounds){
        
        if(cpu.getBoundsInParent().intersects(cpuBounds.getBoundsInParent())){
              
            
            
        } else{
            
            cpuPos.setFill(new ImagePattern(cpuImg));
            
            parentContainer.getChildren().add(cpu);
            parentContainer.getChildren().remove(cpu);
            draggableMaker.makeDraggable(motherboard);
            
        }
    }
    
    public void checkCollisionMotherboard(Rectangle motherboard, Rectangle pcCase){
        
        if(motherboard.getBoundsInParent().intersects(pcCase.getBoundsInParent())){
            
        } else{
            
            
            pcCase.setFill(new ImagePattern(boardImg));
            
            parentContainer.getChildren().add(motherboard);
            parentContainer.getChildren().remove(motherboard);
            
            bringOnButton();
        }
    }
   
    
     
    /* Restarts drag
    public void checkCollisionCPU(Rectangle cpu, Rectangle cpuPos){
        
        if(cpu.getBoundsInParent().intersects(cpuPos.getBoundsInParent())){
            
            } else {
            cpu.setLayoutX(cpuPos.getLayoutX());
            cpu.setLayoutY(cpuPos.getLayoutY());
            cpuPos.setFill(new ImagePattern(boardImg)); // Change the fill of the CPU socket to the motherboard image
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
