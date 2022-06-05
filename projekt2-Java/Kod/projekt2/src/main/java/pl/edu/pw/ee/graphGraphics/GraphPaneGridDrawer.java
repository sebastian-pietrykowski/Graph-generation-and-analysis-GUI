package pl.edu.pw.ee.graphGraphics;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pl.edu.pw.ee.Graph;

public class GraphPaneGridDrawer {
    
    private int parentWidth;
    private int parentHeight;
    private int cellDimension;

    private GraphPane graphPane;
    private Graph graph;
    private StackPane[][] cells;

    private int numberOfGridRows;
    private int numberOfGridColumns;
    
    
    public final int defaultNumberOfCellsDimension = 11;
    public static final Color paneBackgroundColor = Color.SILVER;
    public static final Color cellFillColor = Color.WHITESMOKE;


    public GraphPaneGridDrawer( Graph graph, int parentWidth, int parentHeight ) {
        this.graph = graph;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.cellDimension = parentWidth / defaultNumberOfCellsDimension;
    }

    public GraphPane create() {
        
        this.graphPane = new GraphPane();

        graphPane.setMinSize(parentWidth, parentHeight);

        graphPane.setPadding(new Insets(1, 1, 1, 1));
        graphPane.setVgap(1);
        graphPane.setHgap(1);

        setDimensionsSizes( graph.getRows(), graph.getColumns() );
        setSizeOfCellsIdentical();

        graphPane.setBackground(new Background(new BackgroundFill(paneBackgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        createCells();
        graphPane.setCells(cells);
        graphPane.setGraph(graph);
        
        createColumnNumbersLabels();
        createRowNumbersLabels();
        
        return graphPane;
    }

    public StackPane[][] getCells() { return cells; }

    public int getCellDimension() { return cellDimension; }

    private void setDimensionsSizes( int numberOfRowsInGraph, int numberOfColumnsInGraph ) {
        this.numberOfGridRows = numberOfRowsInGraph > 5 ? numberOfRowsInGraph*2 : defaultNumberOfCellsDimension;
        this.numberOfGridColumns = numberOfColumnsInGraph > 5 ? numberOfColumnsInGraph*2 : defaultNumberOfCellsDimension;
    }

    private void setSizeOfCellsIdentical() {

        graphPane.getRowConstraints().clear();
        graphPane.getColumnConstraints().clear();

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(cellDimension);
        rc.setMaxHeight(cellDimension);

        for (int i = 0; i < numberOfGridRows; i++) {
            graphPane.getRowConstraints().add(rc);
        }

        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth(cellDimension);
        cc.setMaxWidth(cellDimension);

        for (int i = 0; i < numberOfGridColumns; i++) {
            graphPane.getColumnConstraints().add(cc);
        }
    }

    private void createCells() {
        cells = new StackPane[numberOfGridRows][numberOfGridColumns];
        for (int rowIndex = 0; rowIndex < numberOfGridRows; rowIndex++)
            for (int columnIndex = 0; columnIndex < numberOfGridColumns; columnIndex++) {
                cells[rowIndex][columnIndex] = new StackPane();
                cells[rowIndex][columnIndex].setBackground(
                        new Background(new BackgroundFill(cellFillColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        graphPane.add(cells[rowIndex][columnIndex], columnIndex, rowIndex);
            }
    }

    private void createColumnNumbersLabels() {
        // horizontal
        int numberOfDisplayedColumnNumbers = graph.getColumns() > 5 ? graph.getColumns() : GraphPaneController.defaultNumberOfVerticesDimension;
        for (int i = 0; i < numberOfDisplayedColumnNumbers; i++) {
            Label label = new Label("" + (i + 1));
            label.setAlignment(Pos.CENTER);
            graphPane.setHalignment(label, HPos.CENTER);
            cells[0][1 + i * 2].getChildren().add(label);
        }
    }

    private void createRowNumbersLabels() {
        // vertical
        int numberOfDisplayedRowNumbers = graph.getRows() > 5 ? graph.getRows() : GraphPaneController.defaultNumberOfVerticesDimension;
        for (int i = 0; i < numberOfDisplayedRowNumbers; i++) {
            Label label = new Label("" + (i + 1));
            label.setAlignment(Pos.CENTER);
            graphPane.setHalignment(label, HPos.CENTER);
            cells[1 + i * 2][0].getChildren().add(label);
        }
    }

    
}
