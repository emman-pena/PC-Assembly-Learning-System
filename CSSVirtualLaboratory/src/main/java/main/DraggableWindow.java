package main;


import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

class DraggableWindow {

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage;

    public DraggableWindow(Stage stage, Scene scene) {
        this.stage = stage;

        // Add mouse listeners for dragging the window
        scene.setOnMousePressed(this::onMousePressed);
        scene.setOnMouseDragged(this::onMouseDragged);
    }

    private void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    private void onMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
}
