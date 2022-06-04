package pl.edu.pw.ee.pathsOnGraph;

import javafx.scene.paint.Color;
import pl.edu.pw.ee.graphGraphics.ArrowWeightColorPicker;

public class PathOnGraphInfo {

    private PathOnGraph pathOnGraph;
    private int pathNumber;
    private boolean visibilityOnGraph;
    private boolean markedToDelete;
    private Color pathColor;
    
    public PathOnGraphInfo( PathOnGraph pathOnGraph) {
        this.pathOnGraph = pathOnGraph;
        this.visibilityOnGraph = true;
        this.markedToDelete = false;

        ArrowWeightColorPicker colorPicker = new ArrowWeightColorPicker();
        this.pathColor =  colorPicker.getRandomColor();
    }

    public PathOnGraph getPathOnGraph() {
        return pathOnGraph;
    }

    public void setPathNumber( int pathNumber ) {
        this.pathNumber = pathNumber;
    }

    public int getPathNumber() {
        return pathNumber;
    }

    public boolean getVisibilityOnGraph() {
        return visibilityOnGraph;
    }

    public void setVisibilityOnGraph(boolean visibilityOnGraph) {
        this.visibilityOnGraph = visibilityOnGraph;
    }

    public boolean isMarkedToDelete() {
        return markedToDelete;
    }

    public void setMarkedToDelete(boolean markedToDelete) {
        this.markedToDelete = markedToDelete;
    }

    public Color getPathColor() { return this.pathColor; }
}
