package pl.edu.pw.ee.graphGraphics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pl.edu.pw.ee.Edge;
import pl.edu.pw.ee.EdgeWithoutWeight;
import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfo;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfoContainer;

public class EdgesDrawer {
    
    private Graph graph;
    private GraphPane graphPane;
    private int cellDimension;

    private Map<EdgeWithoutWeight,ArrowPane> arrows;
    private ArrowWeightColorPicker arrowWeightColorPicker;
    private PathOnGraphInfoContainer pathInfoContainer;

    public EdgesDrawer( Graph graph, GraphPane graphPane, int cellDimension, PathOnGraphInfoContainer pathInfoContainer ) {
        
        this.pathInfoContainer = pathInfoContainer;
        this.graph = graph;
        this.graphPane = graphPane;
        this.cellDimension = cellDimension;
        this.arrows = new HashMap<>();
    }

    public void drawEdgesWeightColors() {
        arrows.clear();
        // add horizontal edges
        for( int verticalCounter = 0; verticalCounter < graph.getRows(); verticalCounter++)
            for( int horizontalCounter = 1; horizontalCounter < graph.getColumns(); horizontalCounter++ ) {
                int vertex1 = verticalCounter * graph.getColumns() + horizontalCounter - 1;
                int vertex2 = vertex1 + 1;
                
                drawEdgesBetweenVerticesHorizontally( vertex1, vertex2 );
            }
        // add vertical edges
        for( int horizontalCounter = 0; horizontalCounter < graph.getColumns(); horizontalCounter++ )
            for( int verticalCounter = 1; verticalCounter < graph.getRows(); verticalCounter++ ) {
                int vertex1 = (verticalCounter-1) * graph.getColumns() + horizontalCounter;
                int vertex2 = verticalCounter * graph.getColumns() + horizontalCounter;
                drawEdgesBetweenVerticesVertically(vertex1, vertex2);
            } 
    }

    private void drawEdgesBetweenVerticesHorizontally( int vertex1, int vertex2 ) {
        boolean doExistEdgeFrom1To2 = graph.containsEdge(vertex1, vertex2);
        boolean doExistEdgeFrom2To1 = graph.containsEdge(vertex2, vertex1);

        if( doExistEdgeFrom1To2 && doExistEdgeFrom2To1 ) {
            ArrowCellsCoupleSplitVertically splitCell = new ArrowCellsCoupleSplitVertically( graphPane.getEdgeCell(vertex1, vertex2), cellDimension ).splitParent();
            
            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());
            
            splitCell.getUpperPane().drawArrow(edgeFrom1To2ArrowColor);
            splitCell.getLowerPane().drawArrow(edgeFrom2To1ArrowColor);
            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), splitCell.getUpperPane());
            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), splitCell.getLowerPane());
        }
        else if( doExistEdgeFrom1To2 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.HORIZONTAL_TO_RIGHT);

            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), arrowPane);
            arrowPane.drawArrow(edgeFrom1To2ArrowColor);
            graphPane.getEdgeCell(vertex1, vertex2).getChildren().add(arrowPane);
        }
        else if( doExistEdgeFrom2To1 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.HORIZONTAL_TO_LEFT);

            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), arrowPane);
            arrowPane.drawArrow(edgeFrom2To1ArrowColor);
            graphPane.getEdgeCell(vertex2, vertex1).getChildren().add(arrowPane);
        }
    }

    private void drawEdgesBetweenVerticesVertically( int vertex1, int vertex2 ) {
        boolean doExistEdgeFrom1To2 = graph.containsEdge(vertex1, vertex2);
        boolean doExistEdgeFrom2To1 = graph.containsEdge(vertex2, vertex1);

        if( doExistEdgeFrom1To2 && doExistEdgeFrom2To1 ) {
            ArrowCellsCoupleSplitHorizontally splitCell = new ArrowCellsCoupleSplitHorizontally( graphPane.getEdgeCell(vertex1, vertex2), cellDimension ).splitParent();

            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());

            splitCell.getLeftPane().drawArrow(edgeFrom1To2ArrowColor);
            splitCell.getRightPane().drawArrow(edgeFrom2To1ArrowColor);
            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), splitCell.getLeftPane());
            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), splitCell.getRightPane());
        }
        else if( doExistEdgeFrom1To2 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.VERTICAL_TO_DOWN);

            Edge edgeFrom1To2 = graph.getEdge(vertex1, vertex2);
            Color edgeFrom1To2ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom1To2.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex1, vertex2), arrowPane);
            arrowPane.drawArrow(edgeFrom1To2ArrowColor);
            graphPane.getEdgeCell(vertex1, vertex2).getChildren().add(arrowPane);
        }
        else if( doExistEdgeFrom2To1 ) {
            ArrowPane arrowPane = new ArrowPane(cellDimension, cellDimension, ArrowDirections.VERTICAL_TO_UP);

            Edge edgeFrom2To1 = graph.getEdge(vertex2, vertex1);
            Color edgeFrom2To1ArrowColor = arrowWeightColorPicker.determineArrowColor(edgeFrom2To1.getWeight());

            arrows.put(new EdgeWithoutWeight(vertex2, vertex1), arrowPane);
            arrowPane.drawArrow(edgeFrom2To1ArrowColor);
            graphPane.getEdgeCell(vertex2, vertex1).getChildren().add(arrowPane);
        }
    }

    public void disableAllArrows() {
        for (Entry<EdgeWithoutWeight, ArrowPane> entry : arrows.entrySet())
            entry.getValue().disableArrow();
    }
 
    public void markSelectedPaths() {
        disableAllArrows();
        for( PathOnGraphInfo pathInfo: pathInfoContainer.getElements() ) {
            for( EdgeWithoutWeight edge: pathInfo.getPathOnGraph().getEdgesWithoutWeight() ) {
                ArrowPane arrowPane = arrows.get(edge);
                arrowPane.clearPane();
                arrowPane.drawArrow(pathInfo.getPathColor());
            }
        }
    }

    public void markPathAsActual( int pathNumber ) {
        disableAllArrows();
        // temporarily disable arrows
        for( PathOnGraphInfo pathInfo: pathInfoContainer.getElements() ) {
            for( EdgeWithoutWeight edge: pathInfo.getPathOnGraph().getEdgesWithoutWeight() ) {
                ArrowPane arrowPane = arrows.get(edge);
                arrowPane.clearPane();
                arrowPane.temporarilyDisableArrow();
            }
        }
        //mark current path
        PathOnGraphInfo actualPath = pathInfoContainer.getPathOnGraphInfoByPathNumber(pathNumber);
        for( EdgeWithoutWeight edge: actualPath.getPathOnGraph().getEdgesWithoutWeight() ) {
                ArrowPane arrowPane = arrows.get(edge);
                arrowPane.clearPane();
                Edge edgeWithWeight = graph.getEdge(edge.getFromVertex(), edge.getToVertex());
                Color edgeColor = arrowWeightColorPicker.determineArrowColor(edgeWithWeight.getWeight());
                arrowPane.drawArrow(edgeColor);
        }
    }

    public void determineMaxWeight( Label maxWeightLabel ) {
        double maxWeight = graph.getMaxWeight();
        arrowWeightColorPicker = new ArrowWeightColorPicker(maxWeight);
        String trimmedMaxWeight = (""+maxWeight).substring(0,6);
        maxWeightLabel.setText(""+trimmedMaxWeight);
        maxWeightLabel.setAlignment(Pos.CENTER);
    }
}
