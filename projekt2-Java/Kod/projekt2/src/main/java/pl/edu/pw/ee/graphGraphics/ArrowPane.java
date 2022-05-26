package pl.edu.pw.ee.graphGraphics;



import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Polygon;
import pl.edu.pw.ee.generator.Direction;

public class ArrowPane extends Pane {

    int width, height;

    private static final int spacingLength = 4;

    public ArrowPane(int width, int height) {
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.width = width;
        this.height = height;
     }

/*
    public void drawArrow( int longerCellDimension, int direction ) {

        switch( direction ) {
            case Direction.HORIZONTAL_LEFT:
                //drawHorizontalLeftDirectedArrow(longerCellDimension);
                break;
            case Direction.HORIZONTAL_RIGHT:
                drawHorizontalRightDirectedArrow(longerCellDimension);
                break;
            case Direction.VERTICAL_UP:
                drawVerticalUpDirectedArrow(longerCellDimension);
                break;
            case Direction.VERTICAL_DOWN:
                drawVerticalDownDirectedArrow(longerCellDimension);
                break; 
        }
    }
*/

    public void drawHorizontalRightDirectedArrow() {
        int arrowStartX = spacingLength;
        int arrowEndX = width - spacingLength;
        int arrowLength = arrowEndX - arrowStartX;
        int arrowY = height/2;

        Line line = new Line(arrowStartX, arrowY, arrowEndX, arrowY);
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

    public void drawHorizontalLeftDirectedArrow() {
        int arrowStartX = width - spacingLength;
        int arrowEndX = spacingLength;
        int arrowLength = arrowStartX - arrowEndX;
        int arrowY = height/2;

        Line line = new Line(arrowStartX, arrowY, arrowEndX, arrowY);
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

    public void drawVerticalUpDirectedArrow() {
        int arrowStartY = height-spacingLength;
        int arrowEndY = spacingLength;
        int arrowLength = arrowStartY - arrowEndY;
        int arrowX = width/2;

        Line line = new Line(arrowX, arrowStartY, arrowX, arrowEndY);
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

    public void drawVerticalDownDirectedArrow() {
        int arrowStartY = spacingLength;
        int arrowEndY = height-spacingLength;
        int arrowLength = arrowEndY - arrowStartY;
        int arrowX = width/2;

        Line line = new Line(arrowX, arrowStartY, arrowX, arrowEndY);
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
