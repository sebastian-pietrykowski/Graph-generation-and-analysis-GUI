package pl.edu.pw.ee;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GraphPane extends GridPane {
    int parentWidth;
    int parentHeight;
    int cellDimension;
    StackPane[][] cells;

    
    Graph graph;
    int numberOfColumnsInGraph;
    int numberOfRowsInGraph;
    int numberOfGridColumns;
    int numberOfGridRows;
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
        createCells(numberOfGridColumns, numberOfGridRows);
        
        createColumnNumbersLabels();
        createRowNumbersLabels();
    }

    private void setDimensionsSizes( int numberOfColumnsInGraph, int numberOfRowsInGraph ) {
        this.numberOfColumnsInGraph = numberOfColumnsInGraph;
        this.numberOfRowsInGraph = numberOfRowsInGraph;
        this.numberOfGridColumns = numberOfColumnsInGraph > 11 ? numberOfColumnsInGraph*2 : defaultNumberOfCellsDimension;
        this.numberOfGridRows = numberOfRowsInGraph > 11 ? numberOfRowsInGraph*2 : defaultNumberOfCellsDimension;
    }

    private void setSizeOfCellsIdentical() {

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

    private void createCells(int columns, int rows) {
        cells = new StackPane[columns][rows];
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++) {
                cells[i][j] = new StackPane();
                cells[i][j].setBackground(
                        new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                // cells[i][j].set
                this.add(cells[i][j], i, j);
            }
    }

    private void createColumnNumbersLabels() {
        // horizontal
        int numberOfDisplayedRowNumbers = numberOfColumnsInGraph > 5 ? numberOfColumnsInGraph : defaultNumberOfVerticesDimension;
        for (int i = 0; i < numberOfDisplayedRowNumbers; i++) {
            Label label = new Label("" + (i + 1));
            label.setAlignment(Pos.CENTER);
            setHalignment(label, HPos.CENTER);
            cells[1 + i * 2][0].getChildren().add(label);
        }
    }

    private void createRowNumbersLabels() {
        // vertical
        int numberOfDisplayedRColumnNumbers = numberOfRowsInGraph > 5 ? numberOfRowsInGraph : defaultNumberOfVerticesDimension;
        for (int i = 0; i < numberOfDisplayedRColumnNumbers; i++) {
            Label label = new Label("" + (i + 1));
            label.setAlignment(Pos.CENTER);
            setHalignment(label, HPos.CENTER);
            cells[0][1 + i * 2].getChildren().add(label);
        }
    }
}
