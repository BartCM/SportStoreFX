package sportStore.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main program class
 * @version 1.0
 * @author Bartolome
 * @since 20/05/2021
 */

public class Main extends Application {

    /**
     *
     * @param primaryStage to show the window
     * @throws Exception for exceptions
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("SportStore");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    /**
     *
     * @param args to display the format
     */
    public static void main(String[] args) {
        launch(args);
    }
}
