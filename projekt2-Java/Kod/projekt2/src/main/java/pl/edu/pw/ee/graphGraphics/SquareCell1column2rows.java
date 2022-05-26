package pl.edu.pw.ee.graphGraphics;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class SquareCell1column2rows {

    StackPane parentCell;
    int cellDimension;
    ArrowPane upperPane;
    ArrowPane lowerPane;

    public SquareCell1column2rows( StackPane parentCell, int cellDimension ) {
        this.parentCell = parentCell;
        this.cellDimension = cellDimension;
    }

    public SquareCell1column2rows splitParent() {

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

        this.upperPane = new ArrowPane(cellDimension, cellDimension/2);
        this.lowerPane = new ArrowPane(cellDimension, cellDimension/2);
        
        parentCell.getChildren().add(horizontalEdgesPane);
        horizontalEdgesPane.add(upperPane, 0, 0);
        horizontalEdgesPane.add(lowerPane, 0, 1);

        return this;
    }

    public ArrowPane getUpperPane() { return upperPane; }
    public ArrowPane getLowerPane() { return lowerPane; }
    
}
