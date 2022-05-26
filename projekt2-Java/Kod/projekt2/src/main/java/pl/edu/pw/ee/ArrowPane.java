package pl.edu.pw.ee;

import javafx.scene.layout.StackPane;
import pl.edu.pw.ee.generator.Direction;

public class ArrowPane extends StackPane {

    public ArrowPane() {}


    public void drawArrow( int longerCellDimension, int direction ) {

        switch( direction ) {
            case Direction.HORIZONTAL_LEFT:
                drawHorizontalLeftDirectedArrow(longerCellDimension);
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

    private void drawHorizontalLeftDirectedArrow( int longerCellDimension ) {
        
    }

    private void drawHorizontalRightDirectedArrow( int longerCellDimension ) {

    }

    private void drawVerticalUpDirectedArrow( int longerCellDimension ) {

    }

    private void drawVerticalDownDirectedArrow( int longerCellDimension ) {

    }

    



    
}
