package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.util.Duration;

public class PrimaryController {
    /*
    Image assemblyImg = new Image("/images/cpu-g2857daf09_1920");
    Image disassemblyImg = new Image("images/cyber-g7f45dd535_1920");
    Image fullAssemblyImg = new Image("images/computer-gcff4b8e4a_1920");
    */
    private static Scene scene;
    private static Stage stage;

    // Setter method for scene
    public static void setScene(Scene s) {
        scene = s;
    }

    // Setter method for stage
    public static void setStage(Stage s) {
        stage = s;
    }
    
    DraggableMaker draggableMaker = new DraggableMaker();
    
    @FXML
    private WebView webView1;
    
    @FXML
    private WebView webView2;
    
    @FXML
    private WebView webView3;
    
    
    @FXML
    private WebView moduleWebView;
    
    @FXML
    private Pane loadingScreen;
    
    @FXML
    private Pane webPane1;
    
    @FXML
    private Pane webPane2;
    
    @FXML
    private Pane webPane3;
    
    @FXML 
    private BorderPane window;
    
    @FXML
    private StackPane stackPane;

    @FXML
    private Pane home;

    @FXML
    private Pane virtualLabMenu;

    @FXML
    private Pane library;
    
    @FXML
    private Pane moduleView;
    
    @FXML
    private Pane connectionErrorPane;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnMaximize;
    
    @FXML
    private Button btnMinimize;
    
    @FXML
    private ImageView gifHeader;
    
    WebEngine pdfWebEngine;
    
    //draggable top pane --------------
    
    @FXML
    private AnchorPane topPane;
    
    private double xOffset = 0;
    private double yOffset = 0; 
    
      @FXML
    private void initialize() throws IOException {
        // Add mouse listeners for dragging the window
        topPane.setOnMousePressed(this::onMousePressed);
        topPane.setOnMouseDragged(this::onMouseDragged);
           
        home.setVisible(true);
        virtualLabMenu.setVisible(false);
        library.setVisible(false);
        moduleView.setVisible(false);
        
        webPane1.setVisible(true);
        webPane2.setVisible(false);
        webPane3.setVisible(false);
        
        //Load the web content in the WebView
        
        WebEngine webEngine1 = webView1.getEngine();
        webEngine1.load("https://www.computerworld.com/news/");
        
        WebEngine webEngine2 = webView2.getEngine();
        webEngine2.load("https://www.tomshardware.com/news/");
        
        WebEngine webEngine3 = webView3.getEngine();
        webEngine3.load("https://www.pcmag.com/news/");
        
        // Create a PauseTransition with a duration of 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(5));

        // Set the action to be performed after the delay
        delay.setOnFinished(event -> {
            try {
                endLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start the delay
        delay.play();
        /*
        WebEngine webEngine1 = webView1.getEngine();
        webEngine1.load("https://www.computerworld.com/news/");
    webEngine1.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == Worker.State.SUCCEEDED) {
            try {
                endLoading();
            } catch (IOException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });

    WebEngine webEngine2 = webView2.getEngine();
    webEngine2.load("https://www.tomshardware.com/news/");
    webEngine2.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == Worker.State.SUCCEEDED) {
            try {
                endLoading();
            } catch (IOException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });

    WebEngine webEngine3 = webView3.getEngine();
    webEngine3.load("https://www.pcmag.com/news/");
    webEngine3.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == Worker.State.SUCCEEDED) {
            try {
                endLoading();
            } catch (IOException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
 */
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
    // -------------------------
    
    //switch center pane
    @FXML
    private void switchToHome() {
        //home.toFront();
        home.setVisible(true);
        virtualLabMenu.setVisible(false);
        library.setVisible(false);
        moduleView.setVisible(false);
        
    }

    @FXML
    private void switchToVirtualLab() {
        //virtualLabMenu.toFront();
        home.setVisible(false);
        virtualLabMenu.setVisible(true);
        library.setVisible(false);
        moduleView.setVisible(false);
        
    }

    @FXML
    private void switchToLibrary() {
        //library.toFront();
        home.setVisible(false);
        virtualLabMenu.setVisible(false);
        library.setVisible(true);
        moduleView.setVisible(false);
    }
    
    @FXML
    private void switchToMbAssemblyModule() {
        //library.toFront();
        home.setVisible(false);
        virtualLabMenu.setVisible(false);
        library.setVisible(false);
        moduleView.setVisible(true);
          
        pdfWebEngine = moduleWebView.getEngine();
        pdfWebEngine.load("https://www.wikihow.com/Install-a-Motherboard");
    }
    
    @FXML
    private void switchToMbDisassemblyModule() {
        //library.toFront();
        home.setVisible(false);
        virtualLabMenu.setVisible(false);
        library.setVisible(false);
        moduleView.setVisible(true);
          
        pdfWebEngine = moduleWebView.getEngine();
        pdfWebEngine.load("https://www.crucial.com/articles/pc-builders/disassemble-and-rebuild-a-desktop-computer");
    }
    
    @FXML
    private void switchToPcAssemblyModule() {
        //library.toFront();
        home.setVisible(false);
        virtualLabMenu.setVisible(false);
        library.setVisible(false);
        moduleView.setVisible(true);
          
        pdfWebEngine = moduleWebView.getEngine();
        pdfWebEngine.load("https://www.dummies.com/article/technology/computers/basic-skills/how-to-connect-your-keyboard-and-mouse-to-your-pc-202058/");
    }
    
    @FXML
    private void switchToPcMaintenanceModule() {
        //library.toFront();
        home.setVisible(false);
        virtualLabMenu.setVisible(false);
        library.setVisible(false);
        moduleView.setVisible(true);
          
        pdfWebEngine = moduleWebView.getEngine();
        pdfWebEngine.load("https://us.norton.com/blog/how-to/computer-maintenance");
    }
    
    @FXML
    private void showConnectionErrorPane() {
        connectionErrorPane.setVisible(true);
        window.setVisible(false);
        
    }
    //loading screen
    @FXML
    private void endLoading() throws IOException {
         Platform.runLater(() -> {
        loadingScreen.setVisible(false);
        });
    }
    
    //switch window
    @FXML
    private void switchToAssembly() throws IOException {
        App.setRoot("assembly");
    }
    
    @FXML
    private void switchToDisassembly() throws IOException {
        App.setRoot("disassembly");
    }
    
    @FXML
    private void switchToPCAssembly() throws IOException {
        App.setRoot("pcAssembly");
    }

    //switch webpane
    @FXML
    private void switchToWebPane1() {
        
        webPane1.setVisible(true);
        webPane2.setVisible(false);
        webPane3.setVisible(false);
    }
    
    @FXML
    private void switchToWebPane2() {
        
        webPane1.setVisible(false);
        webPane2.setVisible(true);
        webPane3.setVisible(false);
    }
    
    @FXML
    private void switchToWebPane3() {
        
        webPane1.setVisible(false);
        webPane2.setVisible(false);
        webPane3.setVisible(true);
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
     
     
}