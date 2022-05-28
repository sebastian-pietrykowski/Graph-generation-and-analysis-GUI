package pl.edu.pw.ee;

import MyExceptions.IllegalVertexException;
import MyExceptions.IllegalWeightException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pl.edu.pw.ee.generator.CompleteGraphGenerator;
import pl.edu.pw.ee.generator.ConnectedGraphGenerator;
import pl.edu.pw.ee.generator.Generator;
import pl.edu.pw.ee.generator.RandomGraphGenerator;
import pl.edu.pw.ee.graphGraphics.GraphPane;

public class PrimaryController {

    public Graph graph;
    public int generatingMode = -1;
    public int columns;
    public int rows;
    public double fromWeight;
    public double toWeight;
    public int start;
    public int end;
    public GraphPane graphPane;

    @FXML
    public Pane graphPaneParent;

    @FXML
    private Button btnLoadGraph;

    @FXML
    private Button btnCheckConnectivity;

    @FXML
    private TextField columnsTextField;

    @FXML
    private TextField maxWeightTextField;

    @FXML
    private TextField minWeightTextField;

    @FXML
    private TextField rowsTextField;

    @FXML
    private Button btnGenerate;

    @FXML
    private RadioButton mode1AllVertices;

    @FXML
    private RadioButton mode2Connective;

    @FXML
    private RadioButton mode3Random;

    @FXML
    private Button btnDefaultFill;

    @FXML
    private Button btnSaveGraph;

    @FXML
    public Label MessageLabel;

    @FXML
    private TextField endTextField;

    @FXML
    private TextField startTextField;

    @FXML
    private CheckBox extendedResultCheckBox;

    @FXML
    private MenuItem HelpMenuItem;

    @FXML
    private Button btncleanPathParameters;

    @FXML
    private Button btncleanGraphParameters;

    @FXML
    private Button btnCleanEverything;
    
  
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
    public void initializeLabelText(){
        MessageLabel.setFont(new Font("System",14));
         MessageLabel.setWrapText(true);
    }
    @FXML
    private void loadGraph(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        //  File initialDirectory = new File(System.getProperty("user.dir") + "\\projekt2-Java\\Kod\\projekt2\\src\\graphs");
        //  fileChooser.setInitialDirectory(initialDirectory);

        fileChooser.setTitle("Zaznacz jeden plik txt do otwarcia");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pliki .txt", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                this.graph = Graph.readGraph(file);
                this.graphPane.setGraph(this.graph);
            } catch (FileNotFoundException e) {
                MessageLabel.setText("Błąd, nie można czytać grafu z pliku.");
  
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Nie można wczytać grafu");
                alert.showAndWait();
            } catch (IllegalVertexException e) {
                MessageLabel.setText("Błąd, ujemny numer wierzchołka w linii nr: " + e.getLineNumber());
  
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd, ujemny numer wierzchołka");
                alert.showAndWait();
            } catch (IllegalWeightException e) {
                MessageLabel.setText("Błąd, ujemna waga krawędzi w linii nr: " + e.getLineNumber());

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd, ujemny waga krawędzi");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                MessageLabel.setText("Błąd, niepoprawny plik");

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd, niepoprawny plik");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void writeGraph(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Zapisz plik");

        File file = fileChooser.showSaveDialog(null);
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        if (file != null) {
            try {
                graph.writeGraph(file);
            } catch (Exception e) {
                MessageLabel.setText("Błąd, niepoprawny plik");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd, nie można zapisać grafu do pliku");
                alert.showAndWait();
            }
        }
        graphPane.setGraph(graph);
    }

    @FXML
    private void checkGraphConnectivity(ActionEvent event) {
        try {
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
        } catch (NullPointerException e) {
            MessageLabel.setText("Błąd, nie można sprawdzić spójności grafu, upewnij się, że wybrany plik jest poprawny.");

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie można sprawdzić spójności grafu");
            alert.setContentText("Upewnij się, że wybrany plik jest poprawny");
            alert.showAndWait();

        }
    }

    @FXML
    private void operatingMode(ActionEvent event) {
        if (mode1AllVertices.isSelected()) {
            this.generatingMode = 1;
        } else if (mode2Connective.isSelected()) {
            this.generatingMode = 2;
        } else {
            this.generatingMode = 3;
        }
    }

    @FXML
    private void generateGraph(ActionEvent event) {
        if (generatingMode == -1) {
            MessageLabel.setText("Błąd, nie wybrano trybu generowania grafu!");
            MessageLabel.setWrapText(true);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie wybrano trybu generowania grafu!");
            alert.showAndWait();
        } else {
            try {
                this.columns = Integer.parseInt(columnsTextField.getText());
                this.rows = Integer.parseInt(rowsTextField.getText());
                this.fromWeight = Double.parseDouble(minWeightTextField.getText());
                this.toWeight = Double.parseDouble(maxWeightTextField.getText());
                switch (this.generatingMode) {
                    case 1: {
                        Generator generator = new CompleteGraphGenerator(this.columns, this.rows, this.fromWeight, this.toWeight);
                        this.graph = generator.generate();
                        MessageLabel.setText(("Wygenerowano graf zgodnie z trybem 1"));
                       
                        break;
                    }
                    case 2: {
                        Generator generator = new ConnectedGraphGenerator(this.columns, this.rows, this.fromWeight, this.toWeight, 0);
                        this.graph = generator.generate();
                        MessageLabel.setText(("Wygenerowano graf zgodnie z trybem 2"));
                        
                        break;
                    }
                    default: {
                        Generator generator = new RandomGraphGenerator(this.columns, this.rows, this.fromWeight, this.toWeight);
                        this.graph = generator.generate();
                        break;
                    }

                }
                graphPane.setGraph(graph);
            } catch (NumberFormatException e) {
                MessageLabel.setText("Błąd, nie podano parametrów dotyczących grafu!");

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Nie podano parametrów dotyczących grafu!");
                alert.showAndWait();

            }

        }
    }

    @FXML
    private void defaultValues(ActionEvent event) {
        this.columns = 5;
        this.rows = 5;
        this.fromWeight = 0;
        this.toWeight = 1;
        columnsTextField.setText("5");

        maxWeightTextField.setText("5");

        minWeightTextField.setText("0");

        rowsTextField.setText("1");
    }

    @FXML
    private void determinePath(ActionEvent event) {
        this.start = Integer.parseInt(startTextField.getText());
        this.end = Integer.parseInt(endTextField.getText());
        if (extendedResultCheckBox.isSelected()) {
            
        } else {

        }
    }

    @FXML
    private void HelpWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelpWindowController.class.getResource("HelpWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Pomoc");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void cleanPathParametrs(ActionEvent event) {
        startTextField.setText("");
        endTextField.setText("");
        extendedResultCheckBox.setSelected(false);

    }

    @FXML
    private void cleanGraphParameters(ActionEvent event) {
        columnsTextField.setText("");
        rowsTextField.setText("");
        minWeightTextField.setText("");
        maxWeightTextField.setText("");
    }

    @FXML
    private void cleanEverything(ActionEvent event) {
        cleanGraphParameters(event);
        cleanPathParametrs(event);
        mode1AllVertices.setSelected(false);
        mode2Connective.setSelected(false);
        mode3Random.setSelected(false);
        

    }

}
