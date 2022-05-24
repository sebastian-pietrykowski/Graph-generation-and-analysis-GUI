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

        setGraph(new Graph(4, 7));

        
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
}
