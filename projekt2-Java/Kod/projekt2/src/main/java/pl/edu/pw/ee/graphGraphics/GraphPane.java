package pl.edu.pw.ee.graphGraphics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import pl.edu.pw.ee.Dijkstra;
import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.EdgeWithoutWeight;
import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfo;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfoContainer;

public class GraphPane extends GridPane {
    private int parentWidth;
    private int parentHeight;
    private int cellDimension;

    private Graph graph;
    private double[] distances;
    private StackPane[][] cells;
    private Map<EdgeWithoutWeight,ArrowPane> arrows;
    private ArrowWeightColorPicker arrowWeightColorPicker;
    private ArrowWeightColorPicker verticesColorPicker;
    private PathOnGraphInfoContainer pathInfoContainer;

    private int numberOfRowsInGraph;
    private int numberOfColumnsInGraph;
    private int numberOfGridRows;
    private int numberOfGridColumns;
    static final int defaultNumberOfVerticesDimension = 5;
    static final int defaultNumberOfCellsDimension = 11;

    public static final Color paneBackgroundColor = Color.SILVER;
    public static final Color cellFillColor = Color.WHITESMOKE;
    public static final Color circleStrokeColor = Color.BLACK;
    public static final Color circleFillColor = Color.LIGHTGRAY;

    public GraphPane(int parentWidht, int parentHeight) {
        this.parentWidth = parentWidht;
        this.parentHeight = parentHeight;
        this.cellDimension = parentWidth / defaultNumberOfCellsDimension;
        this.arrows = new HashMap<>();

        this.setMinSize(parentWidht, parentHeight);

        this.setPadding(new Insets(1, 1, 1, 1));
        this.setVgap(1);
        this.setHgap(1);

        setDimensionsSizes(defaultNumberOfVerticesDimension, defaultNumberOfVerticesDimension);
        setSizeOfCellsIdentical();

        this.setBackground(new Background(new BackgroundFill(paneBackgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        createCells();
        
        createColumnNumbersLabels();
        createRowNumbersLabels();
    }

    private void setDimensionsSizes( int numberOfRowsInGraph, int numberOfColumnsInGraph ) {
        this.numberOfRowsInGraph = numberOfRowsInGraph;
        this.numberOfColumnsInGraph = numberOfColumnsInGraph;
        this.numberOfGridRows = numberOfRowsInGraph > 5 ? numberOfRowsInGraph*2 : defaultNumberOfCellsDimension;
        this.numberOfGridColumns = numberOfColumnsInGraph > 5 ? numberOfColumnsInGraph*2 : defaultNumberOfCellsDimension;
    }

    private void setSizeOfCellsIdentical() {

        this.getRowConstraints().clear();
        this.getColumnConstraints().clear();

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(cellDimension);
        rc.setMaxHeight(cellDimension);

        for (int i = 0; i < numberOfGridRows; i++) {
            this.getRowConstraints().add(rc);
        }

        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth(cellDimension);
        cc.setMaxWidth(cellDimension);

        for (int i = 0; i < numberOfGridColumns; i++) {
            this.getColumnConstraints().add(cc);
        }
    }

    private void createCells() {
        cells = new StackPane[numberOfGridRows][numberOfGridColumns];
        for (int rowIndex = 0; rowIndex < numberOfGridRows; rowIndex++)
            for (int columnIndex = 0; columnIndex < numberOfGridColumns; columnIndex++) {
                cells[rowIndex][columnIndex] = new StackPane();
                cells[rowIndex][columnIndex].setBackground(
                        new Background(new BackgroundFill(cellFillColor, CornerRadii.EMPTY, Insets.EMPTY)));
                this.add(cells[rowIndex][columnIndex], columnIndex, rowIndex);
            }
    }

    private void createColumnNumbersLabels() {
        // horizontal
        int numberOfDisplayedColumnNumbers = numberOfColumnsInGraph > 5 ? numberOfColumnsInGraph : defaultNumberOfVerticesDimension;
        for (int i = 0; i < numberOfDisplayedColumnNumbers; i++) {
            Label label = new Label("" + (i + 1));
            label.setAlignment(Pos.CENTER);
            setHalignment(label, HPos.CENTER);
            cells[0][1 + i * 2].getChildren().add(label);
        }
    }

    private void createRowNumbersLabels() {
        // vertical
        int numberOfDisplayedRowNumbers = numberOfRowsInGraph > 5 ? numberOfRowsInGraph : defaultNumberOfVerticesDimension;
        for (int i = 0; i < numberOfDisplayedRowNumbers; i++) {
            Label label = new Label("" + (i + 1));
            label.setAlignment(Pos.CENTER);
            setHalignment(label, HPos.CENTER);
            cells[1 + i * 2][0].getChildren().add(label);
        }
    }

    private StackPane getVertexCell(int vertexNumber) {
        
        int rowInGraphNumber = vertexNumber/numberOfColumnsInGraph;
        int columnInGraphNumber = vertexNumber%numberOfColumnsInGraph;
        
        int rowInGridNumber = (rowInGraphNumber+1)*2-1;
        int columnInGridNumber = (columnInGraphNumber+1)*2-1;

        return cells[rowInGridNumber][columnInGridNumber];
    }

    private StackPane getEdgeCell(int vertex1Number, int vertex2Number) {
        
        int rowInGraphNumberVertex1 = vertex1Number/numberOfColumnsInGraph;
        int columnInGraphNumberVertex1 = vertex1Number%numberOfColumnsInGraph;
        
        int rowInGridNumberVertex1 = (rowInGraphNumberVertex1+1)*2-1;
        int columnInGridNumberVertex1 = (columnInGraphNumberVertex1+1)*2-1;

        int rowInGraphNumberVertex2 = vertex2Number/numberOfColumnsInGraph;
        int columnInGraphNumberVertex2 = vertex2Number%numberOfColumnsInGraph;
        
        int rowInGridNumberVertex2 = (rowInGraphNumberVertex2+1)*2-1;
        int columnInGridNumberVertex2 = (columnInGraphNumberVertex2+1)*2-1;

        int edgeRowInGridNumber = (rowInGridNumberVertex1+rowInGridNumberVertex2)/2;
        int edgeColumnInGridNumber = (columnInGridNumberVertex1+columnInGridNumberVertex2)/2;

        return cells[edgeRowInGridNumber][edgeColumnInGridNumber];
    }

    private Circle createVertexCircle(Color fillColor) {
        Circle circle = new Circle(cellDimension/2 *0.8);
        circle.setStroke(circleStrokeColor);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setFill(fillColor);
        circle.relocate(0,0);
        
        return circle;
    }

    public void setGraph( Graph graph, Label maxWeightLabel, Label maxDistanceLabel, PathOnGraphInfoContainer pathInfoContainer ) {
        this.graph = graph;
        this.pathInfoContainer = pathInfoContainer;
        setDimensionsSizes(graph.getRows(), graph.getColumns());
        setSizeOfCellsIdentical();
        createCells();
        
        createColumnNumbersLabels();
        createRowNumbersLabels();
        
        performDijkstraForVertices(maxDistanceLabel);
        addVerticesCircles();

        determineMaxWeight(maxWeightLabel);

        /*
        // graf 5x5 spójny
        PathOnGraph path =  new Dijkstra(graph).determineShortestPath(0, 13);
        PathOnGraphInfo pathInfo = new PathOnGraphInfo(path);
        pathInfoContainer.addPath(pathInfo);

        PathOnGraph path2 =  new Dijkstra(graph).determineShortestPath(3, 8);
        PathOnGraphInfo pathInfo2 = new PathOnGraphInfo(path2);
        pathInfoContainer.addPath(pathInfo2);

        markSelectedPaths();

        markPathAsActual(2);
        markPathAsActual(1);

        drawEdgesWeightColors();
        */
    }

    private StackPane createCircleWithVertexNumber( int vertexNumber, Color fillColor ) {
        Circle vertexCircle = createVertexCircle(fillColor);
        Text vertexNumberText = new Text(""+vertexNumber);
        //vertexNumberText.setFont(new Font(30));
        vertexNumberText.setBoundsType(TextBoundsType.VISUAL);
        vertexNumberText.setTextAlignment(TextAlignment.CENTER);

        Group group = new Group(vertexCircle);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(group, vertexNumberText);

        StackPane.setAlignment(vertexCircle, Pos.CENTER);
        StackPane.setAlignment(vertexNumberText, Pos.CENTER);
        return pane;
    }

    private void addVerticesCircles() {
        for( int i = 0; i < graph.getNumberOfVertices(); i++) {
            double distanceToVertex = distances[i];
            Color filColor = verticesColorPicker.determineArrowColor(distanceToVertex);
            getVertexCell(i).getChildren().add(createCircleWithVertexNumber(i, filColor));
        }
    }

    private void drawEdgesWeightColors() {
        arrows.clear();
        // add horizontal edges
        for( int verticalCounter = 0; verticalCounter < graph.getRows(); verticalCounter++)
            for( int horizontalCounter = 1; horizontalCounter < graph.getColumns(); horizontalCounter++ ) {
                int vertex1 = verticalCounter * graph.getColumns() + horizontalCounter - 1;
                int vertex2 = vertex1 + 1;
                
                drawEdgesBetweenVerticesHorizontally( vertex1, vertex2 );
            }
        // add vertical edges
        for( int horizontalCounter = 0; horizontalCounter < graph.getColumns(); horizontalCounter++ )
            for( int verticalCounter = 1; verticalCounter < graph.getRows(); verticalCounter++ ) {
                int vertex1 = (verticalCounter-1) * graph.getColumns() + horizontalCounter;
                int vertex2 = verticalCounter * graph.getColumns() + horizontalCounter;
                drawEdgesBetweenVerticesVertically(vertex1, vertex2);
            } 
    }

    private void drawEdgesBetweenVerticesHorizontally( int vertex1, int vertex2 ) {
        boolean doExistEdgeFrom1To2 = graph.containsEdge(vertex1, vertex2);
        boolean doExistEdgeFrom2To1 = graph.containsEdge(vertex2, vertex1);

        if( doExistEdgeFrom1To2 && doExistEdgeFrom2To1 ) {
            ArrowCellsCoupleSplitVertically splitCell = new ArrowCellsCoupleSplitVertically(getEdgeCell(vertex1, vertex2), cellDimension).splitParent();
            
            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());
            
            splitCell.getUpperPane().drawArrow(edgeFrom1To2ArrowColor);
            splitCell.getLowerPane().drawArrow(edgeFrom2To1ArrowColor);
            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), splitCell.getUpperPane());
            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), splitCell.getLowerPane());
        }
        else if( doExistEdgeFrom1To2 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.HORIZONTAL_TO_RIGHT);

            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), arrowPane);
            arrowPane.drawArrow(edgeFrom1To2ArrowColor);
            getEdgeCell(vertex1, vertex2).getChildren().add(arrowPane);
        }
        else if( doExistEdgeFrom2To1 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.HORIZONTAL_TO_LEFT);

            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), arrowPane);
            arrowPane.drawArrow(edgeFrom2To1ArrowColor);
            getEdgeCell(vertex2, vertex1).getChildren().add(arrowPane);
        }
    }

    private void drawEdgesBetweenVerticesVertically( int vertex1, int vertex2 ) {
        boolean doExistEdgeFrom1To2 = graph.containsEdge(vertex1, vertex2);
        boolean doExistEdgeFrom2To1 = graph.containsEdge(vertex2, vertex1);

        if( doExistEdgeFrom1To2 && doExistEdgeFrom2To1 ) {
            ArrowCellsCoupleSplitHorizontally splitCell = new ArrowCellsCoupleSplitHorizontally(getEdgeCell(vertex1, vertex2), cellDimension).splitParent();

            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());

            splitCell.getLeftPane().drawArrow(edgeFrom1To2ArrowColor);
            splitCell.getRightPane().drawArrow(edgeFrom2To1ArrowColor);
            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), splitCell.getLeftPane());
            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), splitCell.getRightPane());
        }
        else if( doExistEdgeFrom1To2 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.VERTICAL_TO_DOWN);

            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), arrowPane);
            arrowPane.drawArrow(edgeFrom1To2ArrowColor);
            getEdgeCell(vertex1, vertex2).getChildren().add(arrowPane);
        }
        else if( doExistEdgeFrom2To1 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.VERTICAL_TO_DOWN);

            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), arrowPane);
            arrowPane.drawArrow(edgeFrom2To1ArrowColor);
            getEdgeCell(vertex2, vertex1).getChildren().add(arrowPane);
        }
    }

    public void disableAllArrows() {
        for (Entry<EdgeWithoutWeight, ArrowPane> entry : arrows.entrySet())
            entry.getValue().disableArrow();
    }
 
    public void markSelectedPaths() {
        disableAllArrows();
        for( PathOnGraphInfo pathInfo: pathInfoContainer.getElements() ) {
            for( EdgeWithoutWeight edge: pathInfo.getPathOnGraph().getEdgesWithoutWeight() ) {
                ArrowPane arrowPane = arrows.get(edge);
                arrowPane.clearPane();
                arrowPane.drawArrow(pathInfo.getPathColor());
            }
        }
    }

    public void markPathAsActual( int pathNumber ) {
        // temporarily disable arrows
        for( PathOnGraphInfo pathInfo: pathInfoContainer.getElements() ) {
            for( EdgeWithoutWeight edge: pathInfo.getPathOnGraph().getEdgesWithoutWeight() ) {
                ArrowPane arrowPane = arrows.get(edge);
                arrowPane.clearPane();
                arrowPane.temporarilyDisableArrow();
            }
        }
        //mark current path
        PathOnGraphInfo actualPath = pathInfoContainer.getPathOnGraphInfoByPathNumber(pathNumber);
        for( EdgeWithoutWeight edge: actualPath.getPathOnGraph().getEdgesWithoutWeight() ) {
                ArrowPane arrowPane = arrows.get(edge);
                arrowPane.clearPane();
                Edge edgeWithWeight = graph.getEdge(edge.getFromVertex(), edge.getToVertex());
                Color edgeColor = arrowWeightColorPicker.determineArrowColor(edgeWithWeight.getWeight());
                arrowPane.drawArrow(edgeColor);
        }
    }

    private void performDijkstraForVertices( Label maxDistanceLabel ) {
        Dijkstra distancesDijkstra = new Dijkstra(graph);
        distancesDijkstra.dijkstra(0);
        distances = distancesDijkstra.getDistances();
        double maxDistance = -1;
        for( double distance: distances)
            if( distance > maxDistance )
                maxDistance = distance;
        verticesColorPicker = new ArrowWeightColorPicker(maxDistance);
        
        String trimmedMaxDistance = (""+maxDistance).substring(0,6);
        maxDistanceLabel.setText(""+trimmedMaxDistance);
        maxDistanceLabel.setAlignment(Pos.CENTER);
    }

    private void determineMaxWeight( Label maxWeightLabel ) {
        double maxWeight = graph.getMaxWeight();
        arrowWeightColorPicker = new ArrowWeightColorPicker(maxWeight);
        drawEdgesWeightColors();
        String trimmedMaxWeight = (""+maxWeight).substring(0,6);
        maxWeightLabel.setText(""+trimmedMaxWeight);
        maxWeightLabel.setAlignment(Pos.CENTER);
    }
    
}
