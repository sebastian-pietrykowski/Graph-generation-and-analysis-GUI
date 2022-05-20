package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class PrimaryController {
    public Graph graph;
    @FXML
    private Button btnLoadGraph;
    @FXML
    private Button MenuItemLoadGraph;
    @FXML 
    private Button btnCheckConnectivity;

    @FXML
    private void loadGraph(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Zaznacz jeden plik txt do otwarcia");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki .txt", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {

                Graph.readGraph(file);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    @FXML
    private void writeGraph(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("\\todo");

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try{
                
            } catch(Exception e){
                
            }
        }

    }
     @FXML
    private void checkGraphConnectivity(ActionEvent event) {
        BFS bfs = new BFS(graph,0);
        System.out.println(bfs.checkConnectivty(bfs));
    }

}
