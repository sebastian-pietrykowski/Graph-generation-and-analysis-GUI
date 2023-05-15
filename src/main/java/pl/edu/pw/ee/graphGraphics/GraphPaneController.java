package pl.edu.pw.ee.graphGraphics;

import javafx.scene.control.Label;
import pl.edu.pw.ee.Graph;
import pl.edu.pw.ee.pathsOnGraph.PathOnGraphInfoContainer;

public class GraphPaneController {
    
    private int parentWidth;
    private int parentHeight;
    private int cellDimension;

    private Graph graph;
    private GraphPane graphPane;
    private CircleWithVertexNumberFactory circleFactory;
    private EdgesDrawer edgesDrawer;
   
    public static final int defaultNumberOfVerticesDimension = 5;

    

    public GraphPaneController(int parentWidth, int parentHeight) {
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.graph = new Graph(defaultNumberOfVerticesDimension, defaultNumberOfVerticesDimension);

        GraphPaneGridDrawer gridDrawer = new GraphPaneGridDrawer(graph, parentWidth, parentHeight);
        this.graphPane = gridDrawer.create();
        this.cellDimension = gridDrawer.getCellDimension();

        this.circleFactory = new CircleWithVertexNumberFactory( cellDimension, graph, graphPane );

        
    }

    public GraphPane getGraphPane() { return graphPane; }
  

    public void setGraph( Graph graph, Label maxWeightLabel, Label maxDistanceLabel, PathOnGraphInfoContainer pathInfoContainer ) {
        this.graph = graph;
        
        this.graphPane = new GraphPaneGridDrawer(graph, parentHeight, parentHeight).create();
        this.circleFactory = new CircleWithVertexNumberFactory( cellDimension, graph, graphPane );
        this.edgesDrawer = new EdgesDrawer(graph, graphPane, cellDimension, pathInfoContainer);

        addVerticesCirclesMarkDistances(0, maxDistanceLabel);
        addVerticesCirclesWithoutDistances();
        determineMaxWeight(maxWeightLabel);
        drawEdgesWeightColors();
        
    }

    public void addVerticesCirclesMarkDistances( int startVertexNumber, Label maxDistanceLabel ) {
        circleFactory.addVerticesCirclesMarkDistances(startVertexNumber, maxDistanceLabel);
    }
    
    public void addVerticesCirclesWithoutDistances() {
        circleFactory.addVerticesCirclesWithoutDistances();
    }



    public void drawEdgesWeightColors() {
        edgesDrawer.drawEdgesWeightColors();
    }

    public void disableAllArrows() {
        edgesDrawer.disableAllArrows();
    }

    public void markSelectedPaths() {
        edgesDrawer.markSelectedPaths();
    }

    public void markPathAsActual( int pathNumber ) {
        edgesDrawer.markPathAsActual(pathNumber);
    }

    public void determineMaxWeight( Label maxWeightLabel ) {
        edgesDrawer.determineMaxWeight(maxWeightLabel);
    }
}
