package pl.edu.pw.ee.graphGraphics;



import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class ArrowPane extends Pane {

    private int width, height;
    private int spacingLength = 4;

    public ArrowPane(int width, int height) {
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.width = width;
        this.height = height;
        this.setBackground(new Background(new BackgroundFill(GraphPane.cellFillColor, CornerRadii.EMPTY, Insets.EMPTY)));
     }

     public ArrowPane(int width, int height, int spacingLenght) {
         this(width, height);
         this.spacingLength = spacingLenght;
     }


    public void drawHorizontalRightDirectedArrow( Color color ) {
        int arrowStartX = spacingLength;
        int arrowEndX = width - spacingLength;
        int arrowLength = arrowEndX - arrowStartX;
        int arrowY = height/2;

        Line line = new Line(arrowStartX, arrowY, arrowEndX, arrowY);
        line.setStroke(color);
        this.getChildren().add(line);

        int polygonBaseX = arrowEndX - arrowLength/3;
        int levelDifferenceToPolygon2Vertices = (int) ((arrowLength/3.)/Math.sqrt(3)*0.8);
        int upperPolygonVertexY = arrowY + levelDifferenceToPolygon2Vertices;
        int lowerPolygonVertexY = arrowY - levelDifferenceToPolygon2Vertices;
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            (double) (arrowEndX+1), (double) (arrowY),
            (double) polygonBaseX, (double) upperPolygonVertexY,
            (double) polygonBaseX, (double) lowerPolygonVertexY
        });
        this.getChildren().add(polygon);
    }

    public void drawHorizontalLeftDirectedArrow( Color color ) {
        int arrowStartX = width - spacingLength;
        int arrowEndX = spacingLength;
        int arrowLength = arrowStartX - arrowEndX;
        int arrowY = height/2;

        Line line = new Line(arrowStartX, arrowY, arrowEndX, arrowY);
        line.setStroke(color);
        this.getChildren().add(line);

        int polygonBaseX = arrowEndX + arrowLength/3;
        int levelDifferenceToPolygon2Vertices = (int) ((arrowLength/3.)/Math.sqrt(3)*0.8);
        int upperPolygonVertexY = arrowY + levelDifferenceToPolygon2Vertices;
        int lowerPolygonVertexY = arrowY - levelDifferenceToPolygon2Vertices;
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            (double) (arrowEndX-1), (double) (arrowY),
            (double) polygonBaseX, (double) upperPolygonVertexY,
            (double) polygonBaseX, (double) lowerPolygonVertexY
        });
        this.getChildren().add(polygon);
    }

    public void drawVerticalUpDirectedArrow( Color color ) {
        int arrowStartY = height-spacingLength;
        int arrowEndY = spacingLength;
        int arrowLength = arrowStartY - arrowEndY;
        int arrowX = width/2;

        Line line = new Line(arrowX, arrowStartY, arrowX, arrowEndY);
        line.setStroke(color);
        this.getChildren().add(line);

        int polygonBaseY = arrowEndY + arrowLength/3;
        int levelDifferenceToPolygon2Vertices = (int) ((arrowLength/3.)/Math.sqrt(3)*0.8);
        int leftPolygonVertexX = arrowX - levelDifferenceToPolygon2Vertices;
        int rightPolygonVertexX = arrowX + levelDifferenceToPolygon2Vertices;
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            (double) (arrowX), (double) (arrowEndY-1),
            (double) leftPolygonVertexX, (double) polygonBaseY,
            (double) rightPolygonVertexX, (double) polygonBaseY
        });
        this.getChildren().add(polygon);
    }

    public void drawVerticalDownDirectedArrow( Color color ) {
        int arrowStartY = spacingLength;
        int arrowEndY = height-spacingLength;
        int arrowLength = arrowEndY - arrowStartY;
        int arrowX = width/2;

        Line line = new Line(arrowX, arrowStartY, arrowX, arrowEndY);
        line.setStroke(color);
        this.getChildren().add(line);

        int polygonBaseY = arrowEndY - arrowLength/3;
        int levelDifferenceToPolygon2Vertices = (int) ((arrowLength/3.)/Math.sqrt(3)*0.8);
        int leftPolygonVertexX = arrowX + levelDifferenceToPolygon2Vertices;
        int rightPolygonVertexX = arrowX - levelDifferenceToPolygon2Vertices;
        
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {
            (double) (arrowX), (double) (arrowEndY+1),
            (double) leftPolygonVertexX, (double) polygonBaseY,
            (double) rightPolygonVertexX, (double) polygonBaseY
        });
        this.getChildren().add(polygon);
    }    
}
