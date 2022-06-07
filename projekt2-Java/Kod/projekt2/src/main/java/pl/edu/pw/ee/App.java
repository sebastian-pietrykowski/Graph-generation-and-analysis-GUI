package pl.edu.pw.ee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        scene = new Scene(fxmlLoader.load(), 730, 750);

        PrimaryController controller = fxmlLoader.getController();
        controller.initializeGraphPane(true);
        controller.setWeightGradientPane();
        controller.initializeLabelText();
        controller.initializePathInfoContainer();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Program projektu 2");
    }

    public static void main(String[] args) {
        launch();
    }

}
