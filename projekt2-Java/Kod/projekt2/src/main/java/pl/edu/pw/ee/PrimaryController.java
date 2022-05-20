package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class PrimaryController {

    /*  @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }*/
    @FXML
    private Button btnLoadGraph;

    @FXML
    void loadGraph(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Zaznacz jeden plik txt do otwarcia");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki .txt", "*.txt"));
        try {
            File file = fileChooser.showOpenDialog(null);

            Graph.readGraph(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
