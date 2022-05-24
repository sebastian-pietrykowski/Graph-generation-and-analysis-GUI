package pl.edu.pw.ee;

import javafx.scene.layout.AnchorPane;
import pl.edu.pw.ee.generator.Direction;

public class Arrow {

    private int direction;
    
    private int vertexFrom;
    private int vertexTo;

    AnchorPane paneToDraw;

    public Arrow( int vertexFrom, int vertexTo, AnchorPane paneToDraw, int direction ) {
        this.vertexFrom = vertexFrom;
        this.vertexTo = vertexTo;
        this.paneToDraw = paneToDraw;
        this.direction = direction;
    }


    public void drawArrow( int cellDimension ) {

        switch( direction ) {
            case Direction.HORIZONTAL_LEFT:
                drawHorizontalLeftDirectedArrow(cellDimension);
                break;
            case Direction.HORIZONTAL_RIGHT:
                drawHorizontalRightDirectedArrow(cellDimension);
                break;
            case Direction.VERTICAL_UP:
                drawVerticalUpDirectedArrow(cellDimension);
                break;
            case Direction.VERTICAL_DOWN:
                drawVerticalDownDirectedArrow(cellDimension);
                break; 
        }
    }

    private void drawHorizontalLeftDirectedArrow( int cellDimension ) {
        
    }

    private void drawHorizontalRightDirectedArrow( int cellDimension ) {

    }

    private void drawVerticalUpDirectedArrow( int cellDimension ) {

    }

    private void drawVerticalDownDirectedArrow( int cellDimension ) {

    }

    



    
}
