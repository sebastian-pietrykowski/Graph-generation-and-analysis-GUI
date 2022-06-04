package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pl.edu.pw.ee.MyExceptions.IllegalVertexException;
import pl.edu.pw.ee.MyExceptions.IllegalWeightException;
import pl.edu.pw.ee.generator.CompleteGraphGenerator;
import pl.edu.pw.ee.generator.ConnectedGraphGenerator;
import pl.edu.pw.ee.generator.Generator;
import pl.edu.pw.ee.generator.RandomGraphGenerator;
import pl.edu.pw.ee.graphGraphics.ArrowWeightColorPicker;
import pl.edu.pw.ee.graphGraphics.GraphPane;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraph;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfo;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfoContainer;

public class PrimaryController {

    public Graph graph;
    public GraphPane graphPane;
    private PathOnGraphInfoContainer pathInfoContainer;

    public int generatingMode = -1;
    public int columns;
    public int rows;
    public double fromWeight;
    public double toWeight;
    public int start;
    public int end;

    @FXML
    public Pane graphPaneParent;

    @FXML
    private Pane weightGradientPane;

    @FXML
    private Label maxWeightLabel;

    @FXML
    private Label maxDistanceLabel;

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

    // @FXML
    //private MenuItem HelpMenuItem;
    @FXML
    private Button btncleanPathParameters;

    @FXML
    private Button btncleanGraphParameters;

    @FXML
    private Button btnCleanEverything;

    @FXML
    private Button btnDeterminedPaths;

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

    public void setGraph(Graph graph) {
        initializeGraphPane();
        this.pathInfoContainer = new PathOnGraphInfoContainer();
        graphPane.setGraph(graph, maxWeightLabel, maxDistanceLabel, pathInfoContainer);

    }

    public void initializeLabelText() {
        MessageLabel.setFont(new Font("System", 14));
        MessageLabel.setWrapText(true);
    }

    public void setWeightGradientPane() {
        ArrowWeightColorPicker arrowWeightColorPicker = new ArrowWeightColorPicker();
        Paint weightGradient = arrowWeightColorPicker.getGradient();
        this.weightGradientPane.setBackground(new Background(new BackgroundFill(weightGradient, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void initializePathInfoContainer() {
        this.pathInfoContainer = new PathOnGraphInfoContainer();
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
                this.initializeGraphPane();
                this.setGraph(graph);
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
                alert.setHeaderText("Błąd, ujemna waga krawędzi");
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
                this.initializeGraphPane();
                this.setGraph(graph);
            } catch (IOException e) {
                MessageLabel.setText("Błąd, niepoprawny plik");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd, nie można zapisać grafu do pliku");
                alert.showAndWait();
            }
        } else {
            MessageLabel.setText("Błąd, niepoprawny plik");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Błąd, nie można zapisać grafu do pliku");
            alert.showAndWait();
        }
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
                        MessageLabel.setText(("Wygenerowano graf kompletny, zgodnie z trybem 1"));
                        break;
                    }
                    case 2: {
                        Generator generator = new ConnectedGraphGenerator(this.columns, this.rows, this.fromWeight, this.toWeight, 0);
                        this.graph = generator.generate();
                        MessageLabel.setText(("Wygenerowano graf spójny, zgodnie z trybem 2"));
                        break;
                    }
                    default: {
                        Generator generator = new RandomGraphGenerator(this.columns, this.rows, this.fromWeight, this.toWeight);
                        this.graph = generator.generate();
                        MessageLabel.setText(("Wygenerowano graf losowy, zgodnie z trybem 3"));
                        break;
                    }

                }
                this.initializeGraphPane();
                this.setGraph(graph);
            } catch (NumberFormatException e) {
                MessageLabel.setText("Błąd, nie podano parametrów dotyczących grafu lub podana liczba jest zbyt duża!");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Nie podano parametrów dotyczących grafu lub podana liczba jest zbyt duża!");
                alert.showAndWait();
            } catch (IllegalArgumentException e) {
                MessageLabel.setText("Błąd, minimalna waga jest większa niż maksymalna waga !");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Minimalna waga jest większa niż maksymalna waga!");
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
        maxWeightTextField.setText("1");
        minWeightTextField.setText("0");
        rowsTextField.setText("5");
    }

    @FXML
    private void determinePath(ActionEvent event) {

        try {
            this.start = Integer.parseInt(startTextField.getText());
            this.end = Integer.parseInt(endTextField.getText());

            Dijkstra dijkstra = new Dijkstra(graph);
            PathOnGraph pathOnGraph = dijkstra.determineShortestPath(start, end);

            // print message
            if (pathOnGraph == null) {
                MessageLabel.setText("Nie istenieje droga z wierzchołka nr " + start + " do wierzchołka nr " + end);
            } else {
                if (extendedResultCheckBox.isSelected()) {
                    String path = pathOnGraph.getPathToStringExtendedVariant();
                    MessageLabel.setText("Najkrótsza ścieżka z wierzchołka " + start + " do wierzchołka " + end + " to :\n" + path);
                } else {
                    String path = pathOnGraph.getPathToStringStandardVariant();
                    MessageLabel.setText("Najkrótsza ścieżka z wierzchołka " + start + " do wierzchołka " + end + " to :\n" + path);
                }
                PathOnGraphInfo pathInfo = new PathOnGraphInfo(pathOnGraph);
                pathInfoContainer.addPath(pathInfo);
                graphPane.markSelectedPaths();
            }
        } catch (NumberFormatException e) {
            MessageLabel.setText("Błąd, nie podano wierzchołków dla których ma być szukana ścieżka!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie podano wierzchołków dla których ma być szukana ścieżka!");
            alert.showAndWait();
        } catch (NullPointerException e) {
            MessageLabel.setText("Błąd, brak grafu dla którego ma być szukana ścieżka!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Brak grafu dla którego ma być szukana ścieżka!");
            alert.showAndWait();
        } catch (ArrayIndexOutOfBoundsException e) {
            MessageLabel.setText("Błąd, podano niewłasciwe numery wierzchołków!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Podano niewłasciwe numery wierzchołków!");
            alert.showAndWait();
        }
    }

    @FXML
    private void HelpWindow(ActionEvent event) {
        /*
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
         */
    }

    @FXML
    private void cleanPathParametrs(ActionEvent event) {
        startTextField.setText("");
        endTextField.setText("");
        extendedResultCheckBox.setSelected(false);

        if( graph!= null) {
            graphPane.addVerticesCirclesWithoutDistances();
            graphPane.drawEdgesWeightColors();
            pathInfoContainer.clearPathsInfos();
        }
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
        this.initializeGraphPane();
        MessageLabel.setText("");

    }

    @FXML
    void determinedPathsWindow(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(DeterminedPathsWindow.class.getResource("determinedPathsWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            DeterminedPathsWindow controller = fxmlLoader.getController();
            controller.initialize(pathInfoContainer, graphPane, graph );

            Stage stage = new Stage();
            stage.setTitle("Pomoc");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    public void markNodes() {
        if( graph != null )
            graphPane.addVerticesCirclesMarkDistances(0, new Label());
    }

    public void unmarkNodes() {
        if( graph != null )
            graphPane.addVerticesCirclesWithoutDistances();
    }
}
