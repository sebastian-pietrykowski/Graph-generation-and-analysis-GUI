/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.edu.pw.ee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import pl.edu.pw.ee.graphGraphics.GraphPaneController;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfo;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfoContainer;

public class DeterminedPathsWindow {

    @FXML
    private StackPane pathsPaneParent;

    @FXML
    private Button removeButton;

    @FXML
    private Button markPathsButton;

    @FXML
    private Button unmarkPathsButton;

    @FXML
    private Button unmarkNodesButton;

    @FXML
    private Button removeAllButton;

    private PathOnGraphInfoContainer pathInfoContainer;
    private GridPane pathsPane;
    private GraphPaneController graphPaneController;
    private Graph graph;

    private static final int rowHeight = 35;

    public void initialize( PathOnGraphInfoContainer pathInfoContainer, GraphPaneController graphPaneController, Graph graph ) {
        this.pathInfoContainer = pathInfoContainer;
        this.graphPaneController = graphPaneController;
        this.graph = graph;

        pathsPane = new GridPane();
        
        int rowCounter = 0;
        for( PathOnGraphInfo pathInfo: pathInfoContainer.getElements() ) {
            DeterminedPathsRowPane rowPane = new DeterminedPathsRowPane().createPathRow(pathInfo, graphPaneController);
            pathsPane.add(rowPane, 0, rowCounter++);
        }
        
        pathsPaneParent.getChildren().clear();
        pathsPaneParent.getChildren().add(pathsPane);
        
        addActionToButtons();
        
    }

    private void addActionToButtons() {
        
        markPathsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(graph!= null)
                    graphPaneController.markSelectedPaths();
            }
        });
        unmarkPathsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(graph!= null)
                    graphPaneController.drawEdgesWeightColors();
            }
        });
        unmarkNodesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(graph!= null)
                    graphPaneController.addVerticesCirclesWithoutDistances();
            }
        });

        removeAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(graph!= null) {
                    pathInfoContainer.clearPathsInfos();
                    graphPaneController.drawEdgesWeightColors();
                    graphPaneController.addVerticesCirclesWithoutDistances();
                    initialize(pathInfoContainer, graphPaneController, graph);
                }
            }
        });
    }

    public void clickedRemoveButton(ActionEvent event) {
        for( int index = 0; index < pathInfoContainer.getPathsNumber(); index++ ) {
            PathOnGraphInfo pathInfo = pathInfoContainer.getPathOnGraphInfoByArrayIndex(index);
            if( pathInfo.isMarkedToDelete() ) {
                pathInfoContainer.removePathByArrayIndex(index);
                index--;
            }
        }
        initialize(pathInfoContainer, graphPaneController, graph);
    }
}
