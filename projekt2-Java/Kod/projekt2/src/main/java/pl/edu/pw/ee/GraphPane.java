package pl.edu.pw.ee;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphPane extends GridPane {
    int parentWidht;
    int parentHeight;
    public GraphPane( int parentWidht, int parentHeight ) {
        this.parentWidht = parentWidht;
        this.parentHeight = parentHeight;

        this.setMinSize(parentWidht, parentHeight);
        //this.setPrefSize(parentWidht, parentHeight);

        this.setPadding(new Insets(1, 1, 1, 1)); 
        this.setVgap(1); 
        this.setHgap(1);
        //this.setAlignment(Pos.TOP_LEFT);

        setSizeOfCells(10,10);
        
        //this.setAlignment(Pos.CENTER);

        this.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        StackPane[][] cells = new StackPane[11][11];
        for( int i = 0; i < 11; i++)
            for( int j = 0; j < 11; j++) {
                cells[i][j] = new StackPane();
                cells[i][j].setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                //cells[i][j].set
                this.add(cells[i][j], i, j);
            }

        // horizontal
        Label[] horizonalLabels = new Label[5];
        for( int i = 0; i < 5; i++) {
            horizonalLabels[i] = new Label(""+(i+1));
            horizonalLabels[i].setAlignment(Pos.CENTER);
            setHalignment(horizonalLabels[i], HPos.CENTER);
            cells[1+i*2][0].getChildren().add(horizonalLabels[i]);
        }

        // vertical
        Label[] hverticalLabels = new Label[5];
        for( int i = 0; i < 5; i++) {
            hverticalLabels[i] = new Label(""+(i+1));
            hverticalLabels[i].setAlignment(Pos.CENTER);
            setHalignment(hverticalLabels[i], HPos.CENTER);
            cells[0][1+i*2].getChildren().add(hverticalLabels[i]);
        }
        
        

        
        /*
        this.add(new Label("2"), 0, 3 );
        this.add(new Label("3"), 0, 5 );
        this.add(new Label("4"), 0, 7 );
        this.add(new Label("5"), 0, 9 );
        */

    }

    private void setSizeOfCells(int columns, int rows) {
        int rowCount = 11;
        int columnCount = 11;

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight( (int)getMinHeight() / rowCount );
        rc.setMaxHeight( (int)getMinHeight() / rowCount );

        for (int i = 0; i < rowCount; i++) {
            this.getRowConstraints().add(rc);
        }

        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth( (int)getMinWidth() / columnCount );
        cc.setMaxWidth( (int)getMinWidth() / columnCount );

        for (int i = 0; i < columnCount; i++) {
            this.getColumnConstraints().add(cc);
        }
    }
}
