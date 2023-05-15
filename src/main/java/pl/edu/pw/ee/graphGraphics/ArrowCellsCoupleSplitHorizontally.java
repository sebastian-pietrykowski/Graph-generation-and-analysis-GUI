package pl.edu.pw.ee.graphGraphics;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class ArrowCellsCoupleSplitHorizontally {

    StackPane parentCell;
    int cellDimension;
    ArrowPane leftPane;
    ArrowPane rightPane;

    public ArrowCellsCoupleSplitHorizontally( StackPane parentCell, int cellDimension ) {
        this.parentCell = parentCell;
        this.cellDimension = cellDimension;
    }

    public ArrowCellsCoupleSplitHorizontally splitParent() {

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

        this.leftPane = new ArrowPane(cellDimension/2-2, cellDimension-2, ArrowDirections.VERTICAL_TO_DOWN);
        this.rightPane = new ArrowPane(cellDimension/2-2, cellDimension-2, ArrowDirections.VERTICAL_TO_UP);
        
        parentCell.getChildren().add(verticalEdgesPane);
        verticalEdgesPane.add(leftPane, 0, 0);
        verticalEdgesPane.add(rightPane, 1, 0);

        return this;
    }

    public ArrowPane getLeftPane() { return leftPane; }
    public ArrowPane getRightPane() { return rightPane; }
    
}
