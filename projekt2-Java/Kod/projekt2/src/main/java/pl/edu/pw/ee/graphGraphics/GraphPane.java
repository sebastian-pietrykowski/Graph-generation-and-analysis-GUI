package pl.edu.pw.ee.graphGraphics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.Graph;

public class GraphPane extends GridPane {
    int parentWidth;
    int parentHeight;
    int cellDimension;
    StackPane[][] cells;
    Map<Edge,ArrowPane> arrows;

    Graph graph;
    int numberOfRowsInGraph;
    int numberOfColumnsInGraph;
    int numberOfGridRows;
    int numberOfGridColumns;
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

    private Circle createVertexCircle() {
        Circle circle = new Circle(cellDimension/2 *0.8);
        circle.setStroke(circleStrokeColor);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setFill(circleFillColor);
        circle.relocate(0,0);
        
        return circle;
    }

    public void setGraph( Graph graph ) {
        this.graph = graph;
        setDimensionsSizes(graph.getRows(), graph.getColumns());
        setSizeOfCellsIdentical();
        createCells();
        
        createColumnNumbersLabels();
        createRowNumbersLabels();
        
        addVerticesCircles();
        drawEdges();
    }

    private StackPane createCircleWithVertexNumber( int vertexNumber ) {
        Circle vertexCircle = createVertexCircle();
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
        for( int i = 0; i < graph.getNumberOfVertices(); i++)
            getVertexCell(i).getChildren().add(createCircleWithVertexNumber(i));
    }

    private void drawEdges() {
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
            splitCell.getUpperPane().drawArrow(Color.BLACK, false);
            splitCell.getLowerPane().drawArrow(Color.BLACK, false);
            arrows.put(new Edge(vertex1, vertex2), splitCell.getUpperPane());
            arrows.put(new Edge(vertex2, vertex1), splitCell.getLowerPane());
        }
        else if( doExistEdgeFrom1To2 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.HORIZONTAL_TO_RIGHT);
            arrows.put(new Edge(vertex1, vertex2), arrowPane);
            arrowPane.drawArrow(Color.BLACK, false);
            getEdgeCell(vertex1, vertex2).getChildren().add(arrowPane);
        }
        else if( doExistEdgeFrom2To1 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.HORIZONTAL_TO_LEFT);
            arrows.put(new Edge(vertex2, vertex1), arrowPane);
            arrowPane.drawArrow(Color.BLACK, false);
            getEdgeCell(vertex2, vertex1).getChildren().add(arrowPane);
        }
    }

    private void drawEdgesBetweenVerticesVertically( int vertex1, int vertex2 ) {
        boolean doExistEdgeFrom1To2 = graph.containsEdge(vertex1, vertex2);
        boolean doExistEdgeFrom2To1 = graph.containsEdge(vertex2, vertex1);

        if( doExistEdgeFrom1To2 && doExistEdgeFrom2To1 ) {
            ArrowCellsCoupleSplitHorizontally splitCell = new ArrowCellsCoupleSplitHorizontally(getEdgeCell(vertex1, vertex2), cellDimension).splitParent();
            splitCell.getLeftPane().drawArrow(Color.BLACK, false);
            splitCell.getRightPane().drawArrow(Color.BLACK, false);
            arrows.put(new Edge(vertex1, vertex2), splitCell.getLeftPane());
            arrows.put(new Edge(vertex2, vertex1), splitCell.getRightPane());
        }
        else if( doExistEdgeFrom1To2 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.VERTICAL_TO_DOWN);
            arrows.put(new Edge(vertex1, vertex2), arrowPane);
            arrowPane.drawArrow(Color.BLACK, false);
            getEdgeCell(vertex1, vertex2).getChildren().add(arrowPane);
        }
        else if( doExistEdgeFrom2To1 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.VERTICAL_TO_DOWN);
            arrows.put(new Edge(vertex2, vertex1), arrowPane);
            arrowPane.drawArrow(Color.BLACK, false);
            getEdgeCell(vertex2, vertex1).getChildren().add(arrowPane);
        }
    }

    public void markPath() {
        //horizontal line
        for( int vertex = 0; vertex < graph.getColumns(); vertex++ ) {

        }
    }
    
}
