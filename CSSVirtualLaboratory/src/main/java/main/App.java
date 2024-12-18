package main;

/* "here" signifies original code
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
*/

//test code
/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginUI"));

        // Add listener to rootProperty()
        scene.rootProperty().addListener((observable, oldValue, newValue) -> {
            setWindowSize(stage);
        });

        this.stage = stage;

        // Add mouse listeners for dragging the window
        scene.setOnMousePressed(this::onMousePressed);
        scene.setOnMouseDragged(this::onMouseDragged);

        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT); //title bar
        stage.initStyle(StageStyle.TRANSPARENT);

        // Set minimum and maximum dimensions to current dimensions
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        stage.setMaxWidth(stage.getWidth());
        stage.setMaxHeight(stage.getHeight());

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        setWindowSize((Stage) scene.getWindow());
        stage.centerOnScreen(); //center
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static void setWindowSize(Stage stage) {
        Scene scene = stage.getScene();
        if (scene != null) {
            double width = scene.getRoot().prefWidth(-1);
            double height = scene.getRoot().prefHeight(width);
            stage.setWidth(width);
            stage.setHeight(height);
        }
    }

    private void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void onMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/







/**
 * JavaFX App
 */

// Original code marked by "here" 
/*
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginUI"));
        
        // Add listener to rootProperty()
        scene.rootProperty().addListener((observable, oldValue, newValue) -> {
        setWindowSize(stage);
        });
    
        this.stage = stage;
        
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT); //title bar
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        setWindowSize((Stage) scene.getWindow());
        
        stage.centerOnScreen(); //center
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    private static void setWindowSize(Stage stage) {
        Scene scene = stage.getScene();
        if (scene != null) {
            double width = scene.getRoot().prefWidth(-1);
            double height = scene.getRoot().prefHeight(width);
            stage.setWidth(width);
            stage.setHeight(height);
        }
       
    }
    */
    
    /*
    static void setPane(String fxml) throws IOException {
        StackPane stackPane = new StackPane();
        Pane home = new Pane();
        Pane virtualLabMenu = new Pane();
        stackPane.getChildren().addAll(home, virtualLabMenu);
        
    }
    */
    
    /*
     private void switchToHome() throws IOException {
        Button btnHome = new Button();
        Button btnVirtualLabMenu = new Button();
        btnHome.setOnAction(event -> home.toFront());
        btnVirtualLabMenu.setOnAction(event -> virtualLabMenu.toFront());
        
    } */
    
    /* here
    public static void main(String[] args) {
        launch();
       *?
        /*
        Checks if image is in same directory as App.java
        File file = new File("socket.PNG");
        String path = file.getAbsolutePath();
        System.out.println(path);
        File file2 = new File("App.java");
        String path2 = file2.getAbsolutePath();
        System.out.println(path2);
        */
    //here}
    

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("splashScreen"));

        this.stage = stage;
        
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT); //title bar
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        setWindowSize((Stage) scene.getWindow());

        stage.centerOnScreen(); //center
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static void setWindowSize(Stage stage) {
        Scene scene = stage.getScene();
        if (scene != null) {
            double width = scene.getRoot().prefWidth(-1);
            double height = scene.getRoot().prefHeight(width);
            stage.setWidth(width);
            stage.setHeight(height);
        }
    }

    // Switch to different panes
    private static StackPane rootPane;
    private static Pane homePane;
    private static Pane virtualLabMenuPane;

    private static void initPanes() throws IOException {
        rootPane = new StackPane();
        homePane = (Pane) loadFXML("homeUI");
        virtualLabMenuPane = (Pane) loadFXML("virtualLabMenuUI");

        rootPane.getChildren().addAll(homePane, virtualLabMenuPane);
    }

    public static void switchToHome() {
        homePane.toFront();
    }

    public static void switchToVirtualLabMenu() {
        virtualLabMenuPane.toFront();
    }

    public static void main(String[] args) {
        launch(args);
    }





}



