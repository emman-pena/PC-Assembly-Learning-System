

package main;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreenController {

    @FXML
    private void initialize() throws IOException {
        // Create a PauseTransition with a duration of 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(5));

        // Set the action to be performed after the delay
        delay.setOnFinished(event -> {
            try {
                switchToLoginUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start the delay
        delay.play();
    }

    @FXML
    private void switchToLoginUI() throws IOException {
        App.setRoot("loginUI");
    }
}
