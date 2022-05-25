package pl.edu.pw.ee;

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

public class GraphPane extends GridPane {
    int parentWidth;
    int parentHeight;
    int cellDimension;
    StackPane[][] cells;

    
    Graph graph;
    int numberOfRowsInGraph;
    int numberOfColumnsInGraph;
    int numberOfGridRows;
    int numberOfGridColumns;
    static final int defaultNumberOfVerticesDimension = 5;
    static final int defaultNumberOfCellsDimension = 11;

    public GraphPane(int parentWidht, int parentHeight) {
        this.parentWidth = parentWidht;
        this.parentHeight = parentHeight;
        this.cellDimension = parentWidth / defaultNumberOfCellsDimension;

        this.setMinSize(parentWidht, parentHeight);
        // this.setPrefSize(parentWidht, parentHeight);

        this.setPadding(new Insets(1, 1, 1, 1));
        this.setVgap(1);
        this.setHgap(1);
        // this.setAlignment(Pos.TOP_LEFT);

        setDimensionsSizes(defaultNumberOfVerticesDimension, defaultNumberOfVerticesDimension);
        setSizeOfCellsIdentical();

        // this.setAlignment(Pos.CENTER);

        this.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        createCells();
        
        createColumnNumbersLabels();
        createRowNumbersLabels();

        setGraph(new Graph(3,4));

        
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
                        new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
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
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setFill(Color.LIGHTGRAY);
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
        
        //getEdgeCell(3,7).getChildren().add(new Label("a"));            
        splitEdgesCells();
        
        //GridPane g = (GridPane) getEdgeCell(3,7).getChildren().get(0);
        //g.add(new Label("a"), 0, 0);
        //g.add(new Label("a"), 1, 0);
        
        
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

    private void splitEdgesCells() {


        // add horizontal edges' pane
        for( int verticalCounter = 0; verticalCounter < graph.getRows(); verticalCounter++)
            for( int horizontalCounter = 1; horizontalCounter < graph.getColumns(); horizontalCounter++ ) {
                int vertexFrom = verticalCounter * graph.getColumns() + horizontalCounter - 1;
                int vertexTo = vertexFrom + 1;
                
                GridPane horizontalEdgesPane = createHorizontalEdgesPane();
                getEdgeCell(vertexFrom, vertexTo).getChildren().add(horizontalEdgesPane);
                
            }
        // add vertical edges' pane
        for( int horizontalCounter = 0; horizontalCounter < graph.getColumns(); horizontalCounter++ )
            for( int verticalCounter = 1; verticalCounter < graph.getRows(); verticalCounter++ ) {
                int vertexFrom = (verticalCounter-1) * graph.getColumns() + horizontalCounter;
                int vertexTo = verticalCounter * graph.getColumns() + horizontalCounter;

                GridPane verticalEdgesPane = createVerticalEdgesPane();
                getEdgeCell(vertexFrom, vertexTo).getChildren().add(verticalEdgesPane);
            } 
    }

    private GridPane createHorizontalEdgesPane() {
        GridPane horizontalEdgesPane = new GridPane();

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight((cellDimension-1)/2);
        rc.setMaxHeight((cellDimension-1)/2);
        for (int i = 0; i < 2; i++) {
            horizontalEdgesPane.getRowConstraints().add(rc);
        }

        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth((cellDimension-1));
        cc.setMaxWidth((cellDimension-1));
        horizontalEdgesPane.getColumnConstraints().add(cc);

        return horizontalEdgesPane;
    }

    private GridPane createVerticalEdgesPane() {
        GridPane verticalEdgesPane = new GridPane();

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight((cellDimension-1));
        rc.setMaxHeight((cellDimension-1));
        verticalEdgesPane.getRowConstraints().add(rc);
        

        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth((cellDimension-1)/2);
        cc.setMaxWidth((cellDimension-1)/2);
        for (int i = 0; i < 2; i++) {
            verticalEdgesPane.getColumnConstraints().add(cc);
        }

        return verticalEdgesPane;
    }
}
