package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class PrimaryController {

    public Graph graph;

    @FXML
    public Pane graphPaneParent;

    public GraphPane graphPane;

    @FXML
    private Button btnLoadGraph;
    @FXML
    private Button MenuItemLoadGraph;
    @FXML
    private Button btnCheckConnectivity;

    public void initializeGraphPane() {
        int parentWidth = (int) graphPaneParent.getPrefWidth();
        int parentHeight = (int) graphPaneParent.getPrefHeight();

        graphPane = new GraphPane(parentWidth, parentHeight);
        graphPaneParent.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ScrollPane scrollPane = new ScrollPane(graphPane);
        scrollPane.setMinSize(parentWidth, parentHeight);
        scrollPane.setMaxSize(parentWidth, parentHeight);

        this.graphPaneParent.getChildren().add(scrollPane);
    }

    @FXML
    private void loadGraph(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Zaznacz jeden plik txt do otwarcia");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki .txt", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {

                this.graph = Graph.readGraph(file);

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
            try {

            } catch (Exception e) {

            }
        }

    }

    @FXML
    private void checkGraphConnectivity(ActionEvent event) {

        BFS bfs = new BFS(graph, 0);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sprawdzenie spójności");
        if (bfs.checkConnectivty()) {
            alert.setHeaderText("Graf jest spójny");
            alert.showAndWait();
        } else {
            alert.setTitle("Sprawdzenie spójności");
            alert.setHeaderText("Graf jest niespójny");
            alert.showAndWait();
        }
    }
}
