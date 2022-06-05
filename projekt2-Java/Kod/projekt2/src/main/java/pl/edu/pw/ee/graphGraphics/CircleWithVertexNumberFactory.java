package pl.edu.pw.ee.graphGraphics;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import pl.edu.pw.ee.Dijkstra;
import pl.edu.pw.ee.Graph;

public class CircleWithVertexNumberFactory  {
 
    private int cellDimension;
    private Graph graph;
    private double[] distances;
    private GraphPane graphPane;
    private ArrowWeightColorPicker verticesColorPicker;
    public static final Color circleStrokeColor = Color.BLACK;
    public static final Color circleFillColor = Color.LIGHTGRAY;

    public CircleWithVertexNumberFactory( int cellDimension, Graph graph, GraphPane graphPane ) {
        this.cellDimension = cellDimension;
        this.graph = graph;
        this.graphPane = graphPane;
    }
    
    private StackPane createCircleWithVertexNumber( int vertexNumber, Color fillColor ) {
        Circle vertexCircle = createVertexCircle(fillColor);
        Text vertexNumberText = new Text(""+vertexNumber);
        //vertexNumberText.setFont(new Font(30));
        vertexNumberText.setBoundsType(TextBoundsType.VISUAL);
        vertexNumberText.setTextAlignment(TextAlignment.CENTER);

        Group group = new Group(vertexCircle);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(group, vertexNumberText);

        StackPane.setAlignment(vertexCircle, Pos.CENTER);
        StackPane.setAlignment(vertexNumberText, Pos.CENTER);
        return pane;
    }

    private Circle createVertexCircle(Color fillColor) {
        Circle circle = new Circle(cellDimension/2 *0.8);
        circle.setStroke(circleStrokeColor);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setFill(fillColor);
        circle.relocate(0,0);
        
        return circle;
    }

    public void addVerticesCirclesMarkDistances( int startVertexNumber, Label maxDistanceLabel ) {
        performDijkstraForVertices(startVertexNumber, maxDistanceLabel);
        for( int i = 0; i < graph.getNumberOfVertices(); i++) {
            double distanceToVertex = distances[i];
            Color fillColor;
            if( distances[i] == Double.POSITIVE_INFINITY)
                fillColor = Color.RED;
            else
                fillColor = verticesColorPicker.determineArrowColor(distanceToVertex);
                StackPane vertexCircle = this.createCircleWithVertexNumber(i, fillColor);
            graphPane.getVertexCell(i).getChildren().clear();
            graphPane.getVertexCell(i).getChildren().add(vertexCircle);
        }
    }

    public void addVerticesCirclesWithoutDistances() {
        for( int i = 0; i < graph.getNumberOfVertices(); i++) {
            Color fillColor = CircleWithVertexNumberFactory.circleFillColor;
            StackPane vertexCircle = this.createCircleWithVertexNumber(i, fillColor);
            graphPane.getVertexCell(i).getChildren().clear();
            graphPane.getVertexCell(i).getChildren().add(vertexCircle);
        }
    }
    
    private void performDijkstraForVertices( int startVertexNumber, Label maxDistanceLabel ) {
        Dijkstra distancesDijkstra = new Dijkstra(graph);
        distancesDijkstra.dijkstra(startVertexNumber);
        distances = distancesDijkstra.getDistances();
        double maxDistance = -1;
        for( double distance: distances)
            if( (distance > maxDistance) && (distance < Double.POSITIVE_INFINITY) )
                maxDistance = distance;
        verticesColorPicker = new ArrowWeightColorPicker(maxDistance);
        
        String trimmedMaxDistance = maxDistance == 0 ? maxDistance+"" : (""+maxDistance).substring(0,6);
        maxDistanceLabel.setText(""+trimmedMaxDistance);
        maxDistanceLabel.setAlignment(Pos.CENTER);
    }
}
