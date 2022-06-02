/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfo;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfoContainer;

public class DeterminedPathsWindow {

    @FXML
    private StackPane pathsPaneParent;

    @FXML
    private Button removeButton;

    private PathOnGraphInfoContainer pathInfoContainer;
    private GridPane pathsPane;
    private static final int rowHeight = 35;
    final ToggleGroup weightVisibilityGroup = new ToggleGroup();

    public void initialize( PathOnGraphInfoContainer pathInfoContainer ) {
        this.pathInfoContainer = pathInfoContainer;
        
        pathsPane = new GridPane();
        
        int rowCounter = 0;
        for( PathOnGraphInfo pathInfo: pathInfoContainer.getElements() ) {
            GridPane rowPane = createPathRow(pathInfo);
            pathsPane.add(rowPane, 0, rowCounter++);
        }
        
        pathsPaneParent.getChildren().add(pathsPane);
    }

    private GridPane createPathRow( PathOnGraphInfo pathInfo) {
        GridPane pane = new GridPane();
        pane.setMinWidth(585);
        pane.setMinHeight(rowHeight);
        setSizeOfColumnsAndRowsIdentical(pane);

        Label pathNumberLabel = new Label( pathInfo.getPathNumber() +"" );
        pane.add(pathNumberLabel, 0, 0);
        

        Rectangle colorRectangle = new Rectangle(30, 30);
        colorRectangle.setFill( pathInfo.getPathColor() );
        pane.add(colorRectangle, 1, 0);

        Label startVertexLabel = new Label( pathInfo.getPathOnGraph().getFromVertex() +"" );
        pane.add(startVertexLabel, 2, 0);

        Label endVertexLabel = new Label( pathInfo.getPathOnGraph().getToVertex() +"" );
        pane.add(endVertexLabel, 3, 0);

        RadioButton weightVisibilityRadioButton = new RadioButton();
        weightVisibilityRadioButton.setToggleGroup(weightVisibilityGroup);
        pane.add(weightVisibilityRadioButton, 4, 0);

        CheckBox deleteCheckBox = new CheckBox();
        pane.add(deleteCheckBox, 5, 0);
        
        return pane;
    }

    private void setSizeOfColumnsAndRowsIdentical( GridPane pane ) {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth(100);
        cc.setMaxWidth(rowHeight);
        cc.setHalignment(HPos.CENTER);

        for (int i = 0; i < 6; i++) {
            pane.getColumnConstraints().add(cc);
        }

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100d);
        rc.setValignment(VPos.CENTER);
        pane.getRowConstraints().add(rc);
        
    }

}
