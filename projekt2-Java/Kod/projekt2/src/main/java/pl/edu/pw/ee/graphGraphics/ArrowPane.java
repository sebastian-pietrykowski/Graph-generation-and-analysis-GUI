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
    private final int standardSpacingLength = 4;
    private final int smallerSpacingLength = 1;
    private int arrowDirection; //specified in ArrowDirections class

    public static final Color disabledArrowColor = Color.BLACK;
    public static final Color temporarilyDisabledArrowColor = Color.DARKGREY;

    public ArrowPane(int width, int height, int arrowDirection ) {
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.width = width;
        this.height = height;
        this.arrowDirection = arrowDirection;
        this.setBackground(new Background(new BackgroundFill(GraphPane.cellFillColor, CornerRadii.EMPTY, Insets.EMPTY)));
     }

    public void drawArrow( Color arrowColor ) {

        switch(arrowDirection) {
            case ArrowDirections.HORIZONTAL_TO_RIGHT:
                drawHorizontalRightDirectedArrow(arrowColor);
                break;
            case ArrowDirections.HORIZONTAL_TO_LEFT:
                drawHorizontalLeftDirectedArrow(arrowColor);
                break;
            case ArrowDirections.VERTICAL_TO_UP:
                drawVerticalUpDirectedArrow(arrowColor);
                break;
            case ArrowDirections.VERTICAL_TO_DOWN:
                drawVerticalDownDirectedArrow(arrowColor);
                break;
        }

    }

    private void drawHorizontalRightDirectedArrow( Color arrowColor ) {
        int arrowStartX = standardSpacingLength;
        int arrowEndX = width - standardSpacingLength;
        int arrowLength = arrowEndX - arrowStartX;
        int arrowY = height/2;

        Line line = new Line(arrowStartX, arrowY, arrowEndX, arrowY);
        line.setStroke(arrowColor);
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
        polygon.setFill(arrowColor);
        this.getChildren().add(polygon);
    }

    private void drawHorizontalLeftDirectedArrow( Color arrowColor ) {
        int arrowStartX = width - standardSpacingLength;
        int arrowEndX = standardSpacingLength;
        int arrowLength = arrowStartX - arrowEndX;
        int arrowY = height/2;

        Line line = new Line(arrowStartX, arrowY, arrowEndX, arrowY);
        line.setStroke(arrowColor);
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
        polygon.setFill(arrowColor);
        this.getChildren().add(polygon);
    }

    private void drawVerticalUpDirectedArrow( Color arrowColor ) {
        int arrowStartY = height-standardSpacingLength;
        int arrowEndY = standardSpacingLength;
        int arrowLength = arrowStartY - arrowEndY;
        int arrowX = width/2;

        Line line = new Line(arrowX, arrowStartY, arrowX, arrowEndY);
        line.setStroke(arrowColor);
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
        polygon.setFill(arrowColor);
        this.getChildren().add(polygon);
    }

    private void drawVerticalDownDirectedArrow( Color arrowColor ) {
        int arrowStartY = standardSpacingLength;
        int arrowEndY = height-standardSpacingLength;
        int arrowLength = arrowEndY - arrowStartY;
        int arrowX = width/2;

        Line line = new Line(arrowX, arrowStartY, arrowX, arrowEndY);
        line.setStroke(arrowColor);
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
        polygon.setFill(arrowColor);
        this.getChildren().add(polygon);
    }

    public void clearPane() {
        this.getChildren().clear();
    }

    /**
     * Used to mark edge not contained in any path in PathOnGraphInfoContainer object.
     */
    public void disableArrow() {
        this.clearPane();
        this.drawArrow(disabledArrowColor);
    }

    /**
     * Used to mark edge contained in any path in PathOnGraphInfoContainer object,
     * but not the actual path.
     */
    public void temporarilyDisableArrow() {
        this.clearPane();
        this.drawArrow(temporarilyDisabledArrowColor);
    }
}
