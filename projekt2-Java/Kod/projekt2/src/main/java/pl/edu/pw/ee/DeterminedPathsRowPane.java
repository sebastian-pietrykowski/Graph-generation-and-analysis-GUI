package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import pl.edu.pw.ee.graphGraphics.GraphPaneController;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfo;

public class DeterminedPathsRowPane extends GridPane{
    
    public static final int rowHeight = 35;
    private PathOnGraphInfo pathInfo;
    private GraphPaneController graphPaneController;

    public DeterminedPathsRowPane createPathRow( PathOnGraphInfo pathInfo, GraphPaneController graphPaneController ) {

        this.pathInfo = pathInfo;
        this.graphPaneController = graphPaneController;

        this.setMinWidth(585);
        this.setMinHeight(rowHeight);
        setSizeOfColumnsAndRowsIdentical();

        createNumberOfPathLabel();
        createColorRectangle();
        createStartVertexLabel();
        createEndVertexLabel();
        createDeleteCheckBox();
        setPaneClickedAction();
        
        return this;
    }

    private void setSizeOfColumnsAndRowsIdentical() {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth(585/5.);
        cc.setMaxWidth(rowHeight);
        cc.setHalignment(HPos.CENTER);

        for (int i = 0; i < 5; i++) {
            this.getColumnConstraints().add(cc);
        }

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100d);
        rc.setValignment(VPos.CENTER);
        this.getRowConstraints().add(rc);
    }

    private void createNumberOfPathLabel() {
        Label pathNumberLabel = new Label( pathInfo.getPathNumber() +"" );
        this.add(pathNumberLabel, 0, 0);
    }

    private void createColorRectangle() {
        Rectangle colorRectangle = new Rectangle(30, 30);
        colorRectangle.setFill( pathInfo.getPathColor() );
        this.add(colorRectangle, 1, 0);
    }
    private void createStartVertexLabel() {
        Label startVertexLabel = new Label( pathInfo.getPathOnGraph().getFromVertex() +"" );
        this.add(startVertexLabel, 2, 0);
    }

    private void createEndVertexLabel() {
        Label endVertexLabel = new Label( pathInfo.getPathOnGraph().getToVertex() +"" );
        this.add(endVertexLabel, 3, 0);
    }

    private void createDeleteCheckBox() {
        final CheckBox deleteCheckBox = new CheckBox();
        
        EventHandler<ActionEvent> selectionEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( deleteCheckBox.isSelected() )
                    markToDelete();
                else
                    unmarkToDelete();
                
            }
        };
        deleteCheckBox.setOnAction(selectionEvent);
        this.add(deleteCheckBox, 5, 0);
    }

    private void markToDelete() {
        this.pathInfo.setMarkedToDelete(true);
    }
    private void unmarkToDelete() {
        this.pathInfo.setMarkedToDelete(false);
    }

    private void setPaneClickedAction() {

        EventHandler<MouseEvent> clickedEvent = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                graphPaneController.markPathAsActual(pathInfo.getPathNumber());
                graphPaneController.addVerticesCirclesMarkDistances(pathInfo.getPathOnGraph().getFromVertex(), new Label());
            }
            
        };
        this.setOnMousePressed(clickedEvent);
    }
}

